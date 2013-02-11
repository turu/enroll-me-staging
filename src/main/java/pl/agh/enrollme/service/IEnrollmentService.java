package pl.agh.enrollme.service;

import pl.agh.enrollme.model.Enroll;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface IEnrollmentService {
    List<Enroll> getEnrollmentList();
}
