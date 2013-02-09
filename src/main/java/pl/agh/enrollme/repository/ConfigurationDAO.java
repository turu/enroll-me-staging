package pl.agh.enrollme.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.EnrollConfiguration;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.Integer;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Repository
public class ConfigurationDAO implements IConfigurationDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationDAO.class.getName());

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void addConfiguration(EnrollConfiguration configuration) throws Exception {

        EnrollConfiguration currentConfiguration = em.find(EnrollConfiguration.class, configuration.getEnroll().getEnrollID());
        if(currentConfiguration != null) {
            LOGGER.info("There was already a configuration with [" + currentConfiguration.getEnroll() + "" +
                    "] , removing it");
            em.remove(currentConfiguration);
        }
        em.persist(configuration);
        LOGGER.info("configuration persisted succesfully");
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public EnrollConfiguration getConfigurationByID(Enroll enroll) {
        // TODO: later change it to the Criteria Query
        Query query = em.createQuery("SELECT eC from EnrollConfiguration eC " +
                "where eC.enroll = :enroll").setParameter("enroll", enroll);
        List<EnrollConfiguration> list = query.getResultList();

        if(list != null && list.size() > 0) {
            //TODO: move the this.getClass.getSimpleName() to the Logger configuration!
            LOGGER.info("Found configuration with given ID [" + enroll.getEnrollID() + "]");
        } else {
            LOGGER.info("No configuration for given ID [" + enroll.getEnrollID() + "]");
        }
        return ( list != null && list.size()>0 ) ? list.get(0) : new EnrollConfiguration();
    }
}
