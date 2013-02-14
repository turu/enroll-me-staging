package pl.agh.enrollme.service;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.model.Teacher;
import pl.agh.enrollme.repository.GenericDAO;
import pl.agh.enrollme.repository.ITeacherDAO;

/**
 * @author Michal Partyka
 */
@Service
public class TeacherService extends GenericEditableService<Teacher> {

    @Autowired
    ITeacherDAO teacherDAO;

    @Override
    public GenericDAO<Teacher> getDAO() {
        return (GenericDAO) teacherDAO;
    }

    public void onEdit(RowEditEvent editEvent) {
        System.out.println("[partyks DEBUG] jestem tu");
        super.onEdit(editEvent);
    }

}
