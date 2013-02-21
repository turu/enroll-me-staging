package pl.agh.enrollme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.controller.groupmanagement.GroupManagementController;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Group;
import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.repository.IEnrollmentDAO;
import pl.agh.enrollme.repository.IGroupDAO;
import pl.agh.enrollme.repository.IPersonDAO;

import javax.faces.bean.ViewScoped;
import java.util.*;

/**
 * @author Rafał Cymerys
 */
@Service
public class GroupManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupManagementService.class);

    @Autowired
    private IEnrollmentDAO enrollmentDAO;

    @Autowired
    private IPersonDAO personDAO;

    @Autowired
    private IGroupDAO groupDAO;


    public GroupManagementController newControllerForEnroll(Enroll enroll) {
        GroupManagementController controller = new GroupManagementController(enroll);
        return controller;
    }

    @Transactional
    public void createGroup(String name, Subject subject) {
        List<Person> people = new ArrayList<>();

        Person currentPerson = personDAO.getCurrentUser();

        LOGGER.debug("Creating new group (" + name + ", " + currentPerson.getId() + ", " + subject.getName() + ")");

        Group group = new Group(name, people, subject);

        /*
         * It happens during transaction and currentPerson is not detached, so this one should store group in database.
         */
        currentPerson.addGroups(group);
    }

}
