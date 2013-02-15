package pl.agh.enrollme.model;

import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.List;

/**
 * @author Rafał Cymerys
 */
public class SelectablePersonDataModel extends ListDataModel<Person> implements SelectableDataModel<Person>, Serializable {

    public SelectablePersonDataModel() {
    }

    public SelectablePersonDataModel(List<Person> list) {
        super(list);
    }

    @Override
    public Object getRowKey(Person o) {
        return o.getId().toString();
    }

    @Override
    public Person getRowData(String s) {
        Integer key = Integer.valueOf(s);
        List<Person> people = (List<Person>)getWrappedData();

        for (Person p : people) {
            if (p.getId() == key) {
                return p;
            }
        }

        return null;
    }
}
