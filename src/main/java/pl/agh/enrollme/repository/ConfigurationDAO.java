package pl.agh.enrollme.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void addConfiguration(EnrollConfiguration configuration) throws Exception {
        //TODO - em.merge, this is for a while only (to check if persist doesnt work when same PK is provided

        //TODO remove later - DEBUG ONLY!
        EnrollConfiguration currentConfiguration = em.find(EnrollConfiguration.class, configuration.getEnroll_ID());
        if(currentConfiguration != null) {
            em.remove(currentConfiguration);
        }
        em.persist(configuration);
        if(!em.contains(configuration)) {
            throw new Exception("KURWA");
        }
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public EnrollConfiguration getConfigurationByID(Integer id) {
        Query query = em.createQuery("SELECT eC from EnrollConfiguration eC " +
                "where eC.enroll_ID = :identity").setParameter("identity", id);
        List<EnrollConfiguration> list = query.getResultList();
        System.out.println("[partyks DEBUG] " + list);

        //DEBUG ONLY
        if(list == null || list.size()==0) {
            System.out.println("[partyks DEBUG] No entry...");
        }
        else {
            System.out.println("[partyks DEBUG] " + list.get(0).getPointsPerTerm());
        }


        return ( list != null && list.size()>0 ) ? list.get(0) : new EnrollConfiguration();
    }
}
