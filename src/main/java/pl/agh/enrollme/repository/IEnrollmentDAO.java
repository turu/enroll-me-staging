package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.Enrollment;
import pl.agh.enrollme.model.Person;

import java.util.List;

public interface IEnrollmentDAO {

	void addEnrollment(Enrollment enrollment);
	void removeEnrollment(Integer id);
	List<Enrollment> listEnrollments();
	
}
