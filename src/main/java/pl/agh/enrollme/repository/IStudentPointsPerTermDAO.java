package pl.agh.enrollme.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.*;

import java.util.List;

/**
 * Author: Piotr Turek
 */
public interface IStudentPointsPerTermDAO extends IGenericDAO<StudentPointsPerTerm> {

    @Transactional
    StudentPointsPerTerm getByPersonAndTerm(Person person, Term term);

    @Transactional
    List<StudentPointsPerTerm> getStudentsAssignedToTerm(Term term);

    @Transactional
    List<StudentPointsPerTerm> getStudentsAssignedToSubjectAndTeacher(Subject subject, Teacher teacher);
}
