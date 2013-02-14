package pl.agh.enrollme.service;

import org.springframework.stereotype.Service;
import pl.agh.enrollme.utils.EnrollmentMode;

/**
 * @author Michal Partyka
 */
@Service
public class EnrollmentModeService {

    private String[] values;

    public EnrollmentModeService() {
        EnrollmentMode[] modes = EnrollmentMode.values();
        values = new String[modes.length];
        for (int i = 0; i < modes.length; i++) {
            values[i] = modes[i].toString();
        }
    }

    public String[] getValues() {
        return values;
    }
}
