package pl.agh.enrollme.model;

import org.primefaces.model.SelectableDataModel;
import org.slf4j.LoggerFactory;

import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.List;

/**
 * provides model for table in main/subject-selection/view
 * @author Michal Partyka
 */
public class SelectableDataModelForSubjects extends ListDataModel<Subject> implements SelectableDataModel<Subject>,
        Serializable {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.
                                                            getLogger(SelectableDataModelForSubjects.class.getName());

    public SelectableDataModelForSubjects() {}

    public SelectableDataModelForSubjects(List<Subject> subjects) {
        super(subjects);
        LOGGER.debug("Constructing selectableDataModel with subjects: " + subjects.toString() + " and hash: "
                + hashCode());
        LOGGER.debug("current list: " + ((List<Subject>) getWrappedData()).toString());
    }

    @Override
    public Object getRowKey(Subject subject) {
        LOGGER.debug("getRokKey for subject: " + subject.toString() + " - " + subject.getSubjectID().toString());
        LOGGER.debug("current list: " + ((List<Subject>) getWrappedData()).toString());
        return subject.getSubjectID().toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Subject getRowData(String rowKey) {
        LOGGER.debug("Get data from row: " + rowKey + " in model with hash: " + hashCode());
        LOGGER.debug("current list RowData: " + ((List<Subject>) getWrappedData()).toString());
        List<Subject> subjects = (List<Subject>) getWrappedData();
        LOGGER.debug("List of subject in getRowData: " + subjects);

        for (Subject subject : subjects) {
            LOGGER.debug("Compare: " + subject.getSubjectID().toString() + " vs " + rowKey);
            if(subject.getSubjectID().toString().equals(rowKey)) {
                return subject;
            }
        }
        return null;
    }
}
