package pl.agh.enrollme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.controller.termmanagement.AdminScheduleController;
import pl.agh.enrollme.model.*;
import pl.agh.enrollme.repository.ISubjectDAO;
import pl.agh.enrollme.repository.ITermDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Piotr Turek
 */
@Service
public class TermManagementService implements ITermManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TermManagementService.class);

    @Autowired
    private ISubjectDAO subjectDAO;

    @Autowired
    private ITermDAO termDAO;

    @Override
    public AdminScheduleController createScheduleController(Enroll enroll) {
        //Enroll configuration of the current enroll
        final EnrollConfiguration enrollConfiguration = enroll.getEnrollConfiguration();

        final List<Subject> subjects = subjectDAO.getSubjectsByEnrollment(enroll);
        LOGGER.debug("Subjects of " + enroll + " enrollment retrieved: " + subjects);

        //list of terms to display
        final List<Term> terms = new ArrayList<>();

        for (Subject s : subjects) {
            final List<Term> termsBySubject = termDAO.getTermsBySubject(s);
            if (termsBySubject.size() > 0) {
                terms.addAll(termsBySubject);
            }
        }
        LOGGER.debug("Terms retrieved (" + terms.size() + " of them): " + terms);

        //creating schedule controller
        final AdminScheduleController scheduleController = new AdminScheduleController(enrollConfiguration, subjects, terms);
        LOGGER.debug("ScheduleController created: " + scheduleController);

        return scheduleController;
    }
}
