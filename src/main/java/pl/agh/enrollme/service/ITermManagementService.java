package pl.agh.enrollme.service;

import pl.agh.enrollme.controller.termmanagement.AdminScheduleController;
import pl.agh.enrollme.model.Enroll;

/**
 * Author: Piotr Turek
 */
public interface ITermManagementService {

    AdminScheduleController createScheduleController(Enroll enroll);

}
