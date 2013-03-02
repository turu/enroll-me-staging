package pl.agh.enrollme.controller;

import org.primefaces.model.UploadedFile;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.agh.enrollme.model.*;
import pl.agh.enrollme.repository.IPersonDAO;
import pl.agh.enrollme.repository.IStudentPointsPerTermDAO;
import pl.agh.enrollme.repository.ISubjectDAO;
import pl.agh.enrollme.repository.ITermDAO;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Michal Partyka
 */
@Controller
public class AntInputFileController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AntTermFileController.class.getName());
    private UploadedFile file;
    private static final String REGEX = "\\[[0-9]+\\]";
    private final Map<Integer, Person> personMap = new HashMap<>(100);
    private final Map<TermPK, Term> termMap = new HashMap<>(300);
    private final Map<Integer, Subject> subjectMap = new HashMap<>();

    @Autowired
    private IPersonDAO personDAO;

    @Autowired
    private ITermDAO termDAO;

    @Autowired
    private IStudentPointsPerTermDAO studentPointsPerTermDAO;

    @Autowired
    private ISubjectDAO subjectDAO;


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file == null) {
            FacesMessage msg = new FacesMessage("Failed!", "No file have been uploaded! Try again?");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        final List<MapTuple> tuples = new ArrayList<>();

        LOGGER.debug("Before getting datas from db");

        final List<Person> persons = personDAO.getList();
        final List<Subject> subjects = subjectDAO.getList();
        final List<Term> terms = termDAO.getList();
        for (Person person: persons) {
            personMap.put(person.getIndeks(), person);
        }
        for (Subject subject: subjects) {
            subjectMap.put(subject.getSubjectID(), subject);
        }
        for (Term term: terms) {
            termMap.put(new TermPK(term.getSubject(), term.getTermPerSubjectID()), term);
        }

        LOGGER.debug("Obtained from db");

        Integer index = 0; //current user index (current while parsing)

        Pattern p = Pattern.compile(REGEX);
        String line;

        try (BufferedReader bufferedReader = new BufferedReader( new InputStreamReader (file.getInputstream()))) {
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                Matcher m = p.matcher(line);
                if (m.lookingAt()) {
                    line = line.substring(1, line.length() -1);
                    LOGGER.debug(" I read index: " + line);
                    index = Integer.parseInt(line);
                } else {
                    String[] assignedTermData = line.split(":");
                    if (assignedTermData.length > 1) {
                        LOGGER.debug("subjectID: " + assignedTermData[0]);
                        LOGGER.debug("TermID: " + assignedTermData[1]);

                        try {
                            tuples.add(assignTerm(index, Integer.parseInt(assignedTermData[0]), Integer.parseInt(assignedTermData[1])));
                        } catch (IllegalStateException e) {
                            FacesMessage msg = new FacesMessage("IllegalStateException: ", e.getMessage());
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                            throw e;
                        }

                    } else {
                        LOGGER.debug("Empty line? ");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            LOGGER.debug("IOException while importing file: ", e);
        }

        int i=0;
        for (MapTuple tuple: tuples) {
            LOGGER.debug("Assigned person - " + i);
            StudentPointsPerTerm studentPointsPerTerm = studentPointsPerTermDAO.getByPersonAndTerm(tuple.getPerson(), tuple.getTerm());
            if (studentPointsPerTerm == null) {
                studentPointsPerTerm = new StudentPointsPerTerm(tuple.getTerm(), tuple.getPerson(), 0, "", true);
                studentPointsPerTermDAO.add(studentPointsPerTerm);
            } else {
                studentPointsPerTerm.setAssigned(true);
                studentPointsPerTermDAO.update(studentPointsPerTerm);
            }
        }
    }

    private MapTuple assignTerm(Integer index, Integer subjectID, Integer termPerSubjectID) {
//        Person person = personDAO.getByIndex(index);
        Person person = personMap.get(index);
        if (person == null) {
            throw new IllegalStateException("there is no person with provided index in DB!. Index: " + index);
        }

//        Term term = termDAO.getByPK(new TermPK(subjectDAO.getByPK(subjectID), termPerSubjectID));
        Term term = termMap.get(new TermPK(subjectMap.get(subjectID), termPerSubjectID));
        if (term == null) {
            throw new IllegalStateException("There is no Term with subjectID: " + subjectID + "termID: " +
                    termPerSubjectID);
        }

        if (!person.getSubjectsSaved().contains(term.getSubject())) {
//            throw new IllegalStateException("Trying assign person for the unsaved subject!");
            LOGGER.error("Trying assign person for the unsaved subject!");
        }

        return new MapTuple(person, term);
//        StudentPointsPerTerm studentPointsPerTerm = studentPointsPerTermDAO.getByPersonAndTerm(person, term);
//        if (studentPointsPerTerm == null) {
//            studentPointsPerTerm = new StudentPointsPerTerm(term, person, 0, "", true);
//            studentPointsPerTermDAO.add(studentPointsPerTerm);
//        } else {
//            studentPointsPerTerm.setAssigned(true);
//            studentPointsPerTermDAO.update(studentPointsPerTerm);
//        }
    }

    private class MapTuple {
        private final Person person;
        private final Term term;

        public MapTuple(Person person, Term term) {
            this.person = person;
            this.term = term;
        }

        public Person getPerson() {
            return person;
        }

        public Term getTerm() {
            return term;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MapTuple)) return false;

            MapTuple mapTuple = (MapTuple) o;

            if (person != null ? !person.equals(mapTuple.person) : mapTuple.person != null) return false;
            if (term != null ? !term.equals(mapTuple.term) : mapTuple.term != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = person != null ? person.hashCode() : 0;
            result = 31 * result + (term != null ? term.hashCode() : 0);
            return result;
        }
    }
}
