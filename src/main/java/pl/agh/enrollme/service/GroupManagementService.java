package pl.agh.enrollme.service;

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

    @Autowired
    private IEnrollmentDAO enrollmentDAO;

    Map<Subject, List<Group>> groups;

    @Transactional
    public void prepareForEnrollment(Enroll enrollment) {
        Enroll retrievedEnrollment = enrollmentDAO.getByPK(enrollment.getEnrollID());

        List<Subject> subjects = retrievedEnrollment.getSubjects();

        groups = new HashMap<>();

        for (Subject subject : subjects) {
            groups.put(subject, subject.getGroups());
        }
    }

    public Set<Subject> getSubjects() {
        return groups.keySet();
    }
}
