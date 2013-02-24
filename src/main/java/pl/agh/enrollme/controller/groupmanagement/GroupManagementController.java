package pl.agh.enrollme.controller.groupmanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Group;
import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.repository.IPersonDAO;
import pl.agh.enrollme.repository.ISubjectDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Rafał Cymerys
 */
public class GroupManagementController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupManagementController.class);

    List<SubjectGroupManagementController> subjectControllers;

    private Enroll enroll;

    private Person currentPerson;

    public GroupManagementController() {

    }

    public GroupManagementController(Enroll enroll, Person currentPerson) {
        this.currentPerson = currentPerson;
        setEnroll(enroll);
    }

    public Enroll getEnroll() {
        return enroll;
    }

    public void setEnroll(Enroll enroll) {
        LOGGER.debug("Setting enroll for controller " + enroll.getEnrollID());
        this.enroll = enroll;

        /* If something crashes here, somebody's probably changed FetchType to LAZY.
         * Just synchronize enrollment with db then */
        List<Subject> subjects = enroll.getSubjects();

        subjectControllers = new ArrayList<>();

        for (Subject subject : subjects) {
            SubjectGroupManagementController controller = new SubjectGroupManagementController(subject, currentPerson);

            subjectControllers.add(controller);
        }
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public List<SubjectGroupManagementController> getSubjectControllers() {
        return subjectControllers;
    }

    public SubjectGroupManagementController getControllerWithSubjectId(Integer subjectId) {
        for (SubjectGroupManagementController controller : subjectControllers) {
            if (controller.getSubject().getSubjectID().equals(subjectId)) {
                return controller;
            }
        }

        return null;
    }

}
