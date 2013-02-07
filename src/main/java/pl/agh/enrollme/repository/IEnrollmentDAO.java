package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.webflow.model.EnrollConfiguration;
import pl.agh.enrollme.webflow.model.Enrollment;

import java.util.List;

public interface IEnrollmentDAO {
    void addEnrollment(Enrollment enrollment);
    List<Enrollment> listEnrollments();
}
