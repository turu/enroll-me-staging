package pl.agh.enrollme.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.EnrollConfiguration;

/**
 * @author Michal Partyka
 */
//TODO: It is rather Service but I will move it later ~partyks
@Repository
public class ConfigurationDAO implements IConfigurationDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationDAO.class.getName());


    @Transactional
    @Override
    public EnrollConfiguration getConfigurationByEnrollment(Enroll enrollment) {
        EnrollConfiguration configuration = enrollment.getEnrollConfiguration();
        return (configuration == null) ? new EnrollConfiguration() : configuration;
    }
}
