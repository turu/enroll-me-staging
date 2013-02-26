package pl.agh.enrollme.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.model.Teacher;
import pl.agh.enrollme.model.Term;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.Collections;

import java.util.List;

/**
 * Author: Piotr Turek
 */
@Repository
public class TermDAO extends GenericDAO<Term> implements ITermDAO {

    @PersistenceContext
    private EntityManager em;

    public TermDAO() {
        super(Term.class);
    }

    @Override
    @Transactional
    public List<Term> getTermsBySubject(Subject subject) {

        final TypedQuery<Term> query = em.createQuery("Select t from Term t where t.subject = :subject",
                Term.class).setParameter("subject", subject);

        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Term> getTermsBySubjectAndTeacher(Subject subject, Teacher teacher) {
        final String queryString =
                "Select t from Term t where t.subject = :subject and t.teacher = :teacher";

        final TypedQuery<Term> query = em
                .createQuery(queryString, Term.class)
                .setParameter("subject", subject)
                .setParameter("teacher", teacher);

        return query.getResultList();
    }

    @Override
    public List<Term> getTermsBySubjectOderByTermID(Subject subject) {
        List<Term> terms = getTermsBySubject(subject);
        Collections.sort(terms);
        return terms;
    }

}
