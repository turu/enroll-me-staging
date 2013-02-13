package pl.agh.enrollme.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Repository
public class EnrollmentDAO implements IEnrollmentDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public List<Enroll> getEnrollments() {
        CriteriaQuery<Enroll> c = em.getCriteriaBuilder().createQuery(Enroll.class);
        Root<Enroll> from = c.from(Enroll.class);
        c.select(from);
        return em.createQuery(c).getResultList();
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

    @Override
    @Transactional
    public void updateEnrollment(Enroll enrollment) {
        em.merge(enrollment);
    }
}
