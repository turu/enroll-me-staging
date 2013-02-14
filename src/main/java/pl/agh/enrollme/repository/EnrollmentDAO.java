package pl.agh.enrollme.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.EnrollConfiguration;
import pl.agh.enrollme.utils.EnrollmentMode;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Repository
public class EnrollmentDAO extends GenericDAO<Enroll> implements IEnrollmentDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public List<Enroll> getEnrollments() {

        Enroll enroll = new Enroll("Enrollment1", EnrollmentMode.ACTIVE, null, null);
        EnrollConfiguration enrollConfiguration = new EnrollConfiguration(enroll, 10, 20, 30, 40);
        enroll.setEnrollConfiguration(enrollConfiguration);
        Enroll[] ret = { enroll };
        return Arrays.asList(ret);

        /*CriteriaQuery<Enroll> c = em.getCriteriaBuilder().createQuery(Enroll.class);
        Root<Enroll> from = c.from(Enroll.class);
        c.select(from);
        return em.createQuery(c).getResultList();*/
    }

    @Override
    @Transactional
    public void persistEnrollment(Enroll enrollment) {
        //TODO: why contains doesn't work? is there better way? ~partyks
        if (em.find(Enroll.class, enrollment.getEnrollID()) != null) {
            em.merge(enrollment);
        } else {
            em.persist(enrollment);
        }
    }

    @Override
    @Transactional
    public void deleteEnrollment(Integer id) {
        Enroll enrollment = em.find(Enroll.class, id);
        if (enrollment != null) {
            em.remove(enrollment);
        }
    }

    public EnrollmentDAO() {
        super(Enroll.class);
    }
}
