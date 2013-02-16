package pl.agh.enrollme.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.model.StudentPointsPerTerm;
import pl.agh.enrollme.model.Term;

/**
 * Author: Piotr Turek
 */
public interface IStudentPointsPerTermDAO extends IGenericDAO<StudentPointsPerTerm> {

    @Transactional
    StudentPointsPerTerm getByPersonAndTerm(Person person, Term term);

}
