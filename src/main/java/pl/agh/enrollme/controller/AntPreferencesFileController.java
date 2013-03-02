package pl.agh.enrollme.controller;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.agh.enrollme.model.*;
import pl.agh.enrollme.repository.IConfigurationDAO;
import pl.agh.enrollme.repository.IPersonDAO;
import pl.agh.enrollme.repository.ISubjectDAO;
import pl.agh.enrollme.repository.ITermDAO;
import pl.agh.enrollme.service.StudentPointsService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michal Partyka
 */
@Controller
public class AntPreferencesFileController {
    private static final File prefFile = new File("/tmp/preferencesFile.txt");
    private static final Logger LOGGER = LoggerFactory.getLogger(AntPreferencesFileController.class.getName());
    private StreamedContent streamedPreferences;
    private Enroll enrollment;

    @Autowired
    private IPersonDAO personDAO;

    @Autowired
    private IConfigurationDAO configurationDAO;

    @Autowired
    private ITermDAO termDAO;

    @Autowired
    private StudentPointsService pointsService;

    @Autowired
    private ISubjectDAO subjectDAO;


    public void generatePreferencesFile(ActionEvent event) {
        try (BufferedWriter output = new BufferedWriter( new FileWriter(prefFile))) {
            output.write("\n" + generatePeoplePreferences(enrollment));
        } catch (IOException e) {
            LOGGER.error("Some IO Problems: ", e);
        }

        try {
            streamedPreferences = new DefaultStreamedContent( new FileInputStream(prefFile));
        } catch (FileNotFoundException e) {
            LOGGER.debug("there is no given file :(: " + prefFile.getName(), e);
        }
        FacesMessage msg = new FacesMessage("Success!", "term file is generated");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private String generatePeoplePreferences(Enroll enrollment) {
        EnrollConfiguration configuration = configurationDAO.getConfigurationByEnrollment(enrollment);

        //Simple validation (it shouldn't be here but no time for making configurationDAO/configuration view working
        //better unfortunetly:
        if (configuration.getPointsPerTerm() < 1) {
            throw new IllegalStateException("There is no proper configuration");
        }

        final Map<Subject, List<Term>> map = new HashMap<>();
        List<Subject> s = subjectDAO.getSubjectsByEnrollment(enrollment);
        for (Subject subject: s) {
            map.put(subject, termDAO.getTermsBySubject(subject));
        }

        LOGGER.debug("After filling map...");
        int coefficient;
        List<Person> people = personDAO.getPeopleWhoSavedPreferencesForCustomEnrollment(enrollment);
        StringBuilder preferences = new StringBuilder();
        for (Person person: people) {
            LOGGER.debug("Next person...");
            //append index to the file [291524]
            preferences.append("[").append(person.getIndeks()).append("]\n");
//            List<Subject> savedSubjects = personDAO.getSavedSubjects(person);
            List<Subject> savedSubjects = person.getSubjectsSaved();
            for (Subject subject: savedSubjects) {
                LOGGER.debug("next subject...");
                if (!subject.getHasInteractive()) {
                    continue;
                }
                //Start every subject line with subjectID and ":" e.g. - 13:
                preferences.append(subject.getSubjectID()).append(":");
//                List<Term> terms = termDAO.getTermsBySubjectOderByTermID(subject);
                List<Term> terms = map.get(subject);
                for (Term term: terms) {
                    LOGGER.debug("next term...");
                    if (term.getCertain()) {
                        continue;
                    }
                    //Append for every term his ID and coefficient of preference: ID,coefficient
                    coefficient =
                            getCoefficient(configuration, pointsService.getPointsAssignedByUserToTheTerm(person, term));
                    if (coefficient != -1) {
                        //if term is signed as impossible by the user, skip it!
                        preferences.append(term.getTermPerSubjectID()).append(",").append(coefficient).
                            append(";");
                    }
                }
                preferences.append("\n");
            }
        }
        return preferences.toString();
    }

    public int getCoefficient(EnrollConfiguration configuration, Integer points) {
        if (points == 0) {
            return configuration.getPointsPerSubject() + configuration.getAdditionalPoints();
        } else if ( points > 0 ) {
            return configuration.getPointsPerSubject() - points;
        } else if ( points == -1 ) {
            return -1;
        }
        throw new IllegalStateException("Points are negative number less than -1: " + points);
    }

    public StreamedContent getStreamedPreferences() {
        return streamedPreferences;
    }

    public void setEnrollment(Enroll enrollment) {
        this.enrollment = enrollment;
    }
}