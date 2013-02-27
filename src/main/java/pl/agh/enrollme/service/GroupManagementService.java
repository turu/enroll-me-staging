package pl.agh.enrollme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.controller.groupmanagement.GroupManagementController;
import pl.agh.enrollme.controller.groupmanagement.SubjectGroupManagementController;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Group;
import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.repository.IEnrollmentDAO;
import pl.agh.enrollme.repository.IGroupDAO;
import pl.agh.enrollme.repository.IPersonDAO;
import pl.agh.enrollme.repository.ISubjectDAO;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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

    @Autowired
    private ISubjectDAO subjectDAO;

    public GroupManagementController newControllerForEnroll(Enroll enroll) {
        Enroll retrievedEnroll = enrollmentDAO.getByPK(enroll.getEnrollID());

        GroupManagementController controller = new GroupManagementController(retrievedEnroll, fetchCurrentPerson());
        return controller;
    }

    @Transactional
    private Person fetchCurrentPerson() {
        Person currentPerson = personDAO.getCurrentUser();
        List<Group> groups = currentPerson.getGroups();
        return currentPerson;
    }

    /**
     * Creates new group with given name for given subject.
     * Group's owner is set to currently logged in user.
     * Returns reference to newly created group.
     * @param name name
     * @param subject subject
     * @return newly created group
     */
    @Transactional
    public Group createGroup(String name, Subject subject) {
        List<Person> people = new ArrayList<>();

        Person currentPerson = personDAO.getCurrentUser();

        LOGGER.debug("Creating new group (" + name + ", " + currentPerson.getId() + ", " + subject.getName() + ")");

        Group group = new Group(name, currentPerson, people, subject);

        /*
         * It happens during transaction and currentPerson is not detached, so this one should store group in database.
         */
//        currentPerson.addGroups(group);

        return group;
    }

    public void deleteGroup(Group group) {
        LOGGER.debug("Deleting group " + group.getName());

        /* We will need to get every person in group and then remove relationship. After that we can remove group */
        for (Person p : group.getPersons()) {
            p.getGroups().remove(group);
            personDAO.update(p);
        }

        group.getSubject().getGroups().remove(group);
        subjectDAO.update(group.getSubject());

        groupDAO.remove(group);
    }

    public void saveControllerState(GroupManagementController controller) {
        LOGGER.debug("Saving groups");
        for (SubjectGroupManagementController subjectController : controller.getSubjectControllers()) {
            saveSubjectControllerState(subjectController);
        }
    }

    @Transactional
    private void saveSubjectControllerState(SubjectGroupManagementController controller) {
        Person currentPerson = personDAO.getCurrentUser();
        Subject subject = controller.getSubject();

        LOGGER.debug("Saving group for subject " + subject.getName());

        Group selectedGroup = controller.getSelectedGroup();

        if (selectedGroup != null) {
            LOGGER.debug("Group for " + subject.getName() + " - " + selectedGroup.getName());
        }

        /* Group has changed */
        if (!currentPerson.getGroups().contains(selectedGroup)) {
            LOGGER.debug("Group has changed");

            /* Checking overflow */
            if ((selectedGroup != null) &&
                    (selectedGroup.getPersons().size() + 1 > selectedGroup.getSubject().getTeamsCapacity())) {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Group " + selectedGroup.getName() + " is already full.", ""));

                LOGGER.debug("Size of selected group - " + selectedGroup.getPersons().size());

                for (Person p : selectedGroup.getPersons()) {
                    LOGGER.debug("Person in group : " + p.getFirstName() + " " + p.getLastName());
                }

                LOGGER.debug("Overflow detected on saving. Removing user from group.");

                selectedGroup.getPersons().remove(currentPerson);
                currentPerson.getGroups().remove(selectedGroup);

                personDAO.update(currentPerson);

                controller.setSelectedGroup(null);

                return;
            }

            /* Remove previous group for that subject */
            Iterator<Group> groupIterator = currentPerson.getGroups().iterator();

            while (groupIterator.hasNext()) {
                Group group = groupIterator.next();
                if (group.getSubject().equals(subject)) {
                    LOGGER.debug("Removing user from group " + group.getName());
                    groupIterator.remove();
                }
            }

            /* Save new group */
            if (selectedGroup != null) {
                LOGGER.debug("Adding user to group " + selectedGroup.getName());
                currentPerson.addGroups(selectedGroup);
            }
            personDAO.update(currentPerson);
        }

    }

    public void removePersonFromGroup(Person person, Group group) {
        // Cannot remove self from group. Subject controller should show warning message.
        if (personDAO.getCurrentUser().equals(person)) {
            return;
        }

        person.getGroups().remove(group);
        group.getPersons().remove(person);

        personDAO.update(person);
        groupDAO.update(group);
    }

    public void onChange() {
        LOGGER.debug("SelectOneMenu changed.");
    }



}
