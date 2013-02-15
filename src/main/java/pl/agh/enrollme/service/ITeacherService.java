package pl.agh.enrollme.service;

import org.primefaces.event.RowEditEvent;

/**
 * @author Michal Partyka
 */
public interface ITeacherService {
    void onEdit(RowEditEvent event);
}
