package pl.agh.enrollme.service;

import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Subject;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface ISubjectService {
    List<Subject> getAvailableSubjectForEnrollment(Enroll enroll);
}
