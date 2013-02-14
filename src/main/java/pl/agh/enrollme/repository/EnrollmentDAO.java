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

    public EnrollmentDAO() {
        super(Enroll.class);
    }
}
