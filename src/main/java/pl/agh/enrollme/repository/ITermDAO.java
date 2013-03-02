package pl.agh.enrollme.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.model.Teacher;
import pl.agh.enrollme.model.Term;

import java.util.List;

/**
 * Author: Piotr Turek
 */
public interface ITermDAO extends IGenericDAO<Term> {

    @Transactional
    List<Term> getTermsBySubject(Subject subject);

    @Transactional
    public List<Term> getTermsBySubjectAndTeacher(Subject subject, Teacher teacher);

    List<Term> getTermsBySubjectOderByTermID(Subject subject);

    @Transactional
    List<Term> getTermsBySubjectOrderByStartTime(Subject subject);
}
