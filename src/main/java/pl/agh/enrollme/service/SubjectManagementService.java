package pl.agh.enrollme.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.repository.IEnrollmentDAO;
import pl.agh.enrollme.repository.ISubjectDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Service
public class SubjectManagementService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SubjectManagementService.class.getName());

    @Autowired
    private IEnrollmentDAO enrollmentDAO;

    @Autowired
    private ISubjectDAO subjectDAO;

    private List<Subject> oldSubjects = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
    private Enroll enrollment;

    public void initialize(Enroll enrollment) {
        this.enrollment = enrollmentDAO.getByPK(enrollment.getEnrollID());
        this.oldSubjects = enrollment.getSubjects();
        oldSubjects = subjects;
    }

    public void saveState() {
        for (Subject subject: oldSubjects) {
            LOGGER.debug("Removing subject: " + subject);
            subjectDAO.remove(subject);
        }
        LOGGER.debug("Setting subjects : " + subjects + " for enrollment: " + enrollment);
        enrollment.setSubjects(subjects);
        LOGGER.debug("Merging enrollment: " + enrollment);
        enrollmentDAO.update(enrollment);
    }

    public void addSubject(Subject subject) {
        LOGGER.debug("I am trying to add subject in the service: " + subject);
        if(subjects.contains(subject)) {
            LOGGER.debug("Subject is already added, do not collect it again!");
            return;
        }
        LOGGER.debug("Adding subject ( " + subject + " ) ended with success");
        subjects.add(subject);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
