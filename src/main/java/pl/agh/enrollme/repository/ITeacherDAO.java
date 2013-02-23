package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.Teacher;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface ITeacherDAO extends IGenericDAO<Teacher> {
    Teacher update(Teacher teacher);
    void add(Teacher teacher);
    List<Teacher> getList();
}
