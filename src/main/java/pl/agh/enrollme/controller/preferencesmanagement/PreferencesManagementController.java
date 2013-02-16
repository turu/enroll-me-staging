package pl.agh.enrollme.controller.preferencesmanagement;

import java.io.Serializable;

/**
 * Author: Piotr Turek
 */
public class PreferencesManagementController implements Serializable {

    private static final long serialVersionUID = -577123547860923877L;

    /**
     * Controller object used to controll EnrollSchedule
     */
    private ScheduleController scheduleController;

    /**
     * Controller object used to controll Statistics Ring Component
     */
    private ProgressRingController ringController;

    public ScheduleController getScheduleController() {
        return scheduleController;
    }

    public void setScheduleController(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    public ProgressRingController getRingController() {
        return ringController;
    }

    public void setRingController(ProgressRingController ringController) {
        this.ringController = ringController;
    }

}
