package pl.agh.enrollme.service;

import org.primefaces.event.RowEditEvent;
import pl.agh.enrollme.repository.GenericDAO;

/**
 * @author Michal Partyka
 */
public interface IGenericEditableService<T> {
    void onEdit(RowEditEvent editEvent);
    GenericDAO<T> getDAO();
}
