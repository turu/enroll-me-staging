package pl.agh.enrollme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Group;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.repository.IEnrollmentDAO;

import javax.faces.bean.ViewScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Rafał Cymerys
 */
@Service
@ViewScoped
public class GroupManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupManagementService.class);

    @Autowired
    private IEnrollmentDAO enrollmentDAO;

    Map<Subject, List<Group>> groups;

    @Transactional
    public void prepareForEnrollment(Enroll enrollment) {
        LOGGER.debug("Preparing for enrollment " + enrollment.getEnrollID());

        /* If something crashes here, somebody's probably changed FetchType to LAZY.
         * Just synchronize enrollment with db then */
        List<Subject> subjects = enrollment.getSubjects();

        groups = new HashMap<>();

        for (Subject subject : subjects) {
            groups.put(subject, subject.getGroups());
        }
    }

    public Set<Subject> getSubjects() {
        return groups.keySet();
    }
}
