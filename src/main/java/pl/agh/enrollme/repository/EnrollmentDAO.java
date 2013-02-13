package pl.agh.enrollme.repository;

import org.springframework.stereotype.Repository;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.EnrollConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Repository
public class EnrollmentDAO implements IEnrollmentDAO {
    @Override
    public List<Enroll> getEnrollments() {
        //TODO: get it from database
        Enroll enroll = new Enroll("Enrollment1", null, null);
        EnrollConfiguration enrollConfiguration = new EnrollConfiguration(enroll, 10, 20, 30, 40);
        enroll.setEnrollConfiguration(enrollConfiguration);
        Enroll[] ret = { enroll };
        return Arrays.asList(ret);
    }
}
