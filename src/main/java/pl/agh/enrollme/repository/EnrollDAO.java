package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.Enroll;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface EnrollDAO {
    List<Enroll> getAvailableEnrollments();
}
