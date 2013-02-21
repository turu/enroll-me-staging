package pl.agh.enrollme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.controller.preferencesmanagement.PreferencesManagementController;
import pl.agh.enrollme.controller.preferencesmanagement.ProgressRingController;
import pl.agh.enrollme.controller.preferencesmanagement.ScheduleController;
import pl.agh.enrollme.model.*;
import pl.agh.enrollme.repository.IPersonDAO;
import pl.agh.enrollme.repository.IStudentPointsPerTermDAO;
import pl.agh.enrollme.repository.ISubjectDAO;
import pl.agh.enrollme.repository.ITermDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Piotr Turek
 */
@Service
public class PreferencesManagementService implements IPreferencesManagementService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PreferencesManagementService.class);

    @Autowired
    private IPersonDAO personDAO;

    @Autowired
    private ISubjectDAO subjectDAO;

    @Autowired
    private ITermDAO termDAO;

    @Autowired
    private IStudentPointsPerTermDAO pointsDAO;

    @Autowired
    private PersonService personService;

    @Override
    public PreferencesManagementController createPreferencesManagementController(Enroll enroll) {
        final Person person = personService.getCurrentUser();
        LOGGER.debug(person + " person retrieved from security context");

        //Enroll configuration of the current enroll
        final EnrollConfiguration enrollConfiguration = enroll.getEnrollConfiguration();

        final List<Subject> subjectsByEnrollment = subjectDAO.getSubjectsByEnrollment(enroll);
        LOGGER.debug("Subjects of " + enroll + " enrollment retrieved: " + subjectsByEnrollment);

        final List<Subject> personSubjects = subjectDAO.getSubjectsByPerson(person);
        LOGGER.debug("Subjects of " + person + " person retrieved: " + personSubjects);

        //list of subjects belonging to the currentEnrollment, choosen by person
        final List<Subject> subjects = new ArrayList<>();

        for (Subject subject : personSubjects) {
            if (subjectsByEnrollment.contains(subject)) {
                subjects.add(subject);
            }
        }
        LOGGER.debug("Intersection found: " + subjects);

        //list of terms to display
        final List<Term> terms = new ArrayList<>();

        for (Subject s : subjects) {
            terms.addAll(termDAO.getTermsBySubject(s));
        }
        LOGGER.debug("Terms retrieved (" + terms.size() + " of them): " + terms);

        //list of currently assigned points
        final List<StudentPointsPerTerm> points = new ArrayList<>();

        for (Term t : terms) {
            points.add(pointsDAO.getByPersonAndTerm(person, t));
        }
        LOGGER.debug("Current preferences retrieved (" + points.size() + " of them): " + points);

        //create missing point per terms so that every term has its sppt (they can be missing because it's the first
        //the user entered preferences-management or there were some zero-point sppt that didn't get persisted)
        createMissingSPPT(terms, points, person);
        LOGGER.debug("Missing point per terms created, there are " + points.size() + " in total.");

        //creating progress ring controller
        final ProgressRingController ringController = new ProgressRingController(enrollConfiguration, subjects, terms, points);
        LOGGER.debug("ProgressRingController created: " + ringController);

        //creating schedule controller
        final ScheduleController scheduleController = new ScheduleController(enrollConfiguration, subjects, terms, points);
        LOGGER.debug("ScheduleController created: " + scheduleController);

        final PreferencesManagementController preferencesController =
                new PreferencesManagementController(scheduleController, ringController);
        LOGGER.debug("PreferencesManagementController created: " + preferencesController);

        return preferencesController;

    }

    private void createMissingSPPT(List<Term> terms, List<StudentPointsPerTerm> points, Person person) {
        Map<TermPK, Boolean> termsPresent = new HashMap<TermPK, Boolean>();

        for (StudentPointsPerTerm sppt : points) {
            final Term term = sppt.getTerm();
            termsPresent.put(term.getTermId(), true);
        }

        for (Term term : terms) {
            Boolean hasPoints = termsPresent.get(term.getTermId());
            if (hasPoints == null || hasPoints == false) {
                points.add(new StudentPointsPerTerm(term, person, 0, "", false));
            }
        }
    }

}
