package pl.agh.enrollme.service;

import org.primefaces.event.RowEditEvent;

/**
 * @author Michal Partyka
 */
public abstract class GenericEditableService<T> implements IGenericEditableService<T> {
    @Override
    @SuppressWarnings("unchecked")
    public void onEdit(RowEditEvent editEvent) {
        System.out.println("[partyks DEBUG] Is in genericEditableService");
        T editedObject = (T) editEvent.getObject();

        if (editedObject != null) {
            System.out.println("[partyks DEBUG] mergin object..." + editedObject.toString());
            getDAO().update(editedObject);
        }
    }
}
