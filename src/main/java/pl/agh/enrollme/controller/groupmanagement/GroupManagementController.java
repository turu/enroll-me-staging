package pl.agh.enrollme.controller.groupmanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Group;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.repository.IEnrollmentDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rafał Cymerys
 */
public class GroupManagementController implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupManagementController.class);

    @Autowired
    private IEnrollmentDAO enrollmentDAO;

    Map<Subject, List<Group>> groups;

    private Enroll enroll;

    public GroupManagementController() {

    }

    public GroupManagementController(Enroll enroll) {
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

        groups = new HashMap<>();

        for (Subject subject : subjects) {
            groups.put(subject, subject.getGroups());
        }
    }

    public List<Subject> getSubjects() {
        return new ArrayList<>(groups.keySet());
    }

    public List<Group> getGroupsForSubject(Subject subject) {
        return groups.get(subject);
    }

    public boolean isCurrentUserInGroupForSubject(Subject subject) {

    }
}
