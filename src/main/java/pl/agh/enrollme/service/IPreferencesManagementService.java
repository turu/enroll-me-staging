package pl.agh.enrollme.service;

import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.controller.preferencesmanagement.PreferencesManagementController;
import pl.agh.enrollme.controller.preferencesmanagement.ScheduleController;
import pl.agh.enrollme.model.Enroll;

/**
 * Author: Piotr Turek
 */
public interface IPreferencesManagementService {

    /**
     * Creates and returns a valid instance of PreferencesManagementController for currentEnroll and Principal.
     * @param currentEnroll
     * @return
     */
    @Transactional
    ScheduleController createScheduleController(Enroll currentEnroll);

    @Transactional
    void saveScheduleController(Enroll currentEnroll, ScheduleController scheduleController);

}
