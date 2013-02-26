package pl.agh.enrollme.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.model.Teacher;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface ITeacherDAO extends IGenericDAO<Teacher> {
    Teacher update(Teacher teacher);
    void add(Teacher teacher);
    List<Teacher> getList();

    @Transactional
    List<Teacher> getTeachersForSubject(Subject subject);
}
