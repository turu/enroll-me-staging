package pl.agh.enrollme.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Repository
public class TeacherDAO extends GenericDAO<Teacher> implements ITeacherDAO {

    @PersistenceContext
    EntityManager em;

    public TeacherDAO() {
        super(Teacher.class);
    }

    @Override
    @Transactional
    public List<Teacher> getTeachersForSubject(Subject subject) {
        final String queryString = "Select distinct teacher from Term t where t.subject = :subject";
        final TypedQuery<Teacher> query = em
                .createQuery(queryString, Teacher.class)
                .setParameter("subject", subject);

        return query.getResultList();
    }
}
