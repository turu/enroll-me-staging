package pl.agh.enrollme.model;

import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.List;

/**
 * @author Michal Partyka
 */
public class SelectableDataModelForSubjects extends ListDataModel<Subject> implements SelectableDataModel<Subject>,
        Serializable {

    public SelectableDataModelForSubjects() {}

    public SelectableDataModelForSubjects(List<Subject> subjects) {
        super(subjects);
    }

    @Override
    public Object getRowKey(Subject subject) {
        return subject.getSubjectID().toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Subject getRowData(String rowKey) {
        List<Subject> subjects = (List<Subject>) getWrappedData();

        for (Subject subject : subjects) {
            if(subject.getSubjectID().toString().equals(rowKey)) {
                return subject;
            }
        }
        return null;
    }
}
