package pl.agh.enrollme.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.EnrollConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Michal Partyka
 */
@Repository
public class ConfigurationDAO /*implements IConfigurationDAO*/{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationDAO.class.getName());

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void addConfiguration(EnrollConfiguration configuration) throws Exception {

        EnrollConfiguration currentConfiguration = em.find(EnrollConfiguration.class,
                configuration.getEnroll().getEnrollID());
        if(currentConfiguration != null) {
            LOGGER.info("There was already a configuration with [" + currentConfiguration.getEnroll().getEnrollID()
                    + "] , removing it");
            em.merge(currentConfiguration);
        } else {
            em.persist(configuration);
        }
        LOGGER.info("configuration persisted succesfully");
    }

    @Transactional
    public EnrollConfiguration getConfigurationByEnrollment(Enroll enrollment) {
        EnrollConfiguration configuration = enrollment.getEnrollConfiguration();
        return (configuration == null) ? new EnrollConfiguration() : configuration;
    }
}
