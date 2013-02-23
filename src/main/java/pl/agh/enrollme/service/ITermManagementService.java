package pl.agh.enrollme.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.controller.termmanagement.AdminScheduleController;
import pl.agh.enrollme.model.Enroll;

/**
 * Author: Piotr Turek
 */
@Service
public interface ITermManagementService {

    @Transactional
    AdminScheduleController createScheduleController(Enroll enroll);

    @Transactional
    void saveScheduleState(AdminScheduleController scheduleController);
}
