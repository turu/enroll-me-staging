package pl.agh.enrollme.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.EnrollConfiguration;

/**
 * @author Michal Partyka
 */
public interface IConfigurationDAO {
    @Transactional
    EnrollConfiguration getConfigurationByEnrollment(Enroll enrollment);
}
