package pl.agh.enrollme.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface IEnrollmentDAO {
    List<Enroll> getEnrollments();
    void persistEnrollment(Enroll enrollment);
    @Transactional
    void deleteEnrollment(Integer id);

    @Transactional
    void updateEnrollment(Enroll editedEnrollment);
}
