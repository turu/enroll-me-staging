package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.Teacher;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface ITeacherDAO {
    void update(Teacher teacher);
    void add(Teacher teacher);
    List<Teacher> getList();
}
