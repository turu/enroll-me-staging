package pl.agh.enrollme.repository;

import org.springframework.stereotype.Repository;
import pl.agh.enrollme.model.Enroll;

/**
 * @author Michal Partyka
 */
@Repository
public class EnrollmentDAO extends GenericDAO<Enroll> implements IEnrollmentDAO {

    public EnrollmentDAO() {
        super(Enroll.class);
    }
}
