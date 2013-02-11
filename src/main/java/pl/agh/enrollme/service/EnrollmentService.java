package pl.agh.enrollme.service;

import org.springframework.stereotype.Service;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.EnrollConfiguration;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Service
public class EnrollmentService implements Serializable, IEnrollmentService {

    private static final long serialVersionUID = -5771235478609230476L;

    @Override
    public List<Enroll> getEnrollmentList() {
        Enroll enroll = new Enroll("Enrollment1", null);
        EnrollConfiguration enrollConfiguration = new EnrollConfiguration(enroll, 10, 20, 30, 40);
        enroll.setEnrollConfiguration(enrollConfiguration);
        Enroll[] ret = { enroll };
        List<Enroll> list = Arrays.asList(ret);
        for(Enroll enroll1 : list) {
            System.out.println("[partyks DEBUG]" + enroll1.getName());
        }
        return list;
    }
}
