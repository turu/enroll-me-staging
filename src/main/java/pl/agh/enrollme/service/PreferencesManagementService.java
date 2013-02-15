package pl.agh.enrollme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.controller.preferencesmanagement.PreferencesManagementController;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.model.Term;
import pl.agh.enrollme.repository.IPersonDAO;
import pl.agh.enrollme.repository.ISubjectDAO;
import pl.agh.enrollme.repository.ITermDAO;

import javax.xml.ws.WebServiceContext;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PreferencesManagementController createPreferencesManagementController(Enroll currentEnroll) {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;

        if(principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        } else {
            LOGGER.warn("Principal " + principal + " is not an instance of UserDetails!");
            throw new SecurityException("Principal " + principal + " is not an instance of UserDetails!");
        }

        final Person person = personDAO.findByUsername(userDetails.getUsername());

        //TODO: Retrieve current preferences of the user (if any)
        //TODO: Create the controller and pass all the above data to it.

        final List<Subject> subjectsByEnrollment = subjectDAO.getSubjectsByEnrollment(currentEnroll);
        final List<Subject> personSubjects = person.getSubjects();

        //list of subjects belonging to the currentEnrollment, choosen by person
        final List<Subject> subjects = new ArrayList<>();

        for (Subject subject : personSubjects) {
            if (subjectsByEnrollment.contains(subject)) {
                subjects.add(subject);
            }
        }

        //list of terms to display
        final List<Term> terms = new ArrayList<>();

        for (Subject s : subjects) {
            terms.addAll(termDAO.getTermsBySubject(s));
        }

        return null;

    }

}
