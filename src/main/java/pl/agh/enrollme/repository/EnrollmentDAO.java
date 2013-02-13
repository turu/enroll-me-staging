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

//
//        if(!em.contains(enroll)) {
//            throw new UnsupportedOperationException("kupa");
//        }

        CriteriaQuery<Enroll> c = em.getCriteriaBuilder().createQuery(Enroll.class);
        Root<Enroll> from = c.from(Enroll.class);
        c.select(from);

//        return em.createQuery("select enroll from Enroll enroll").getResultList();
//        return null;

        return em.createQuery(c).getResultList();
//
//        //TODO: get it from database
//        Enroll enroll = new Enroll("Enrollment1", null, null);
//        EnrollConfiguration enrollConfiguration = new EnrollConfiguration(enroll, 10, 20, 30, 40);
//        enroll.setEnrollConfiguration(enrollConfiguration);
//        Enroll[] ret = { enroll };
//        return Arrays.asList(ret);
    }

    @Override
    @Transactional
    public void persistEnrollment(Enroll enrollment) {
        if (em.contains(enrollment)) {
            em.merge(enrollment);
        } else {
            em.persist(enrollment);
        }
    }

    @Override
    @Transactional
    public void deleteEnrollment(Integer id) {
        System.out.println("[partyks DEBUG] I am here!!!!");
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
