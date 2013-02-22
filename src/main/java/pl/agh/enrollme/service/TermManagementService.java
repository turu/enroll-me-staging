package pl.agh.enrollme.service;

import org.springframework.stereotype.Service;
import pl.agh.enrollme.controller.termmanagement.AdminScheduleController;
import pl.agh.enrollme.model.Enroll;

/**
 * Author: Piotr Turek
 */
@Service
public class TermManagementService implements ITermManagementService {

    @Override
    public AdminScheduleController createScheduleController(Enroll enroll) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
