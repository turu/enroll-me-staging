package pl.agh.enrollme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.controller.groupmanagement.GroupManagementController;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Group;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.repository.IEnrollmentDAO;

import javax.faces.bean.ViewScoped;
import java.util.*;

/**
 * @author Rafał Cymerys
 */
@Service
public class GroupManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupManagementService.class);

    @Autowired
    private IEnrollmentDAO enrollmentDAO;


    public GroupManagementController newControllerForEnroll(Enroll enroll) {
        GroupManagementController controller = new GroupManagementController(enroll);
        return controller;
    }
}
