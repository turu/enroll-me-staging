package pl.agh.enrollme.service;

import pl.agh.enrollme.controller.preferencesmanagement.PreferencesManagementController;
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
    PreferencesManagementController createPreferencesManagementController(Enroll currentEnroll);

}
