package pl.agh.enrollme.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.controller.finalschedule.FinalScheduleController;
import pl.agh.enrollme.controller.preferencesmanagement.PreferencesManagementController;
import pl.agh.enrollme.controller.preferencesmanagement.ScheduleController;
import pl.agh.enrollme.model.Enroll;

/**
 * Author: Piotr Turek
 */
public interface IFinalScheduleService {

    /**
     * Creates and returns a valid instance of FinalScheduleController for currentEnroll and Principal.
     * @param currentEnroll
     * @return
     */
    @Transactional
    FinalScheduleController createScheduleController(Enroll currentEnroll);

}
