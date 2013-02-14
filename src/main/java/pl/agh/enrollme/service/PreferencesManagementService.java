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
import pl.agh.enrollme.repository.IPersonDAO;
import pl.agh.enrollme.repository.ISubjectDAO;
import pl.agh.enrollme.repository.PersonDAO;

import javax.xml.ws.WebServiceContext;

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

    @Override
    public PreferencesManagementController createPreferencesManagementController(Enroll currentEnroll) {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;

        if(principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        } else {
            LOGGER.warn("Principal " + principal + " is not an instance of UserDetails!");
            //TODO: Add some exception handling (throw it actually)
        }

        final Person person = personDAO.findByUsername(userDetails.getUsername());

        //TODO: Retrieve subjects choosen by person for currentEnroll.
        //TODO: Retrieve terms of the subjects.
        //TODO: Retrieve current preferences of the user (if any)
        //TODO: Create the controller and pass all the above data to it.

    }

}
