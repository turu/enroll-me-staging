package pl.agh.enrollme.repository;

import org.springframework.stereotype.Repository;
import pl.agh.enrollme.model.Teacher;

/**
 * @author Michal Partyka
 */
@Repository
public class TeacherDAO extends GenericDAO<Teacher> implements ITeacherDAO {
    public TeacherDAO() {
        super(Teacher.class);
    }
}
