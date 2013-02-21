package pl.agh.enrollme.service;

import org.primefaces.model.SelectableDataModel;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.SelectableDataModelForSubjects;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.repository.ISubjectDAO;

/**
 * @author Michal Partyka
 */
@Controller
public class SubjectChoosingController implements ISubjectChoosingService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SubjectChoosingController.class.getName());
    private Subject[] chosenSubjects;
    private SelectableDataModel<Subject> model;

    @Autowired
    private ISubjectDAO subjectDAO;

    public boolean userAlreadySubmitedSubjects() {
        return false;
    }

    public void setModel(SelectableDataModel<Subject> model) {
        this.model = model;
    }

    public SelectableDataModel<Subject> getModel() {
        return model;
    }

    public void createModel(Enroll enrollment) {
        model = new SelectableDataModelForSubjects(subjectDAO.getSubjectsByEnrollment(enrollment));
    }

    public void setChosenSubjects(Subject[] chosenSubjects) {
        this.chosenSubjects = chosenSubjects;
    }

    public Subject[] getChosenSubjects() {
        return chosenSubjects;
    }
}
