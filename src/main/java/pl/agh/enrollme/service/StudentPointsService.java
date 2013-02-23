package pl.agh.enrollme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.model.StudentPointsPerTerm;
import pl.agh.enrollme.model.Term;
import pl.agh.enrollme.repository.IStudentPointsPerTermDAO;

/**
 * @author Michal Partyka
 */
@Service
public class StudentPointsService {
    @Autowired
    IStudentPointsPerTermDAO studentPointsPerTermDAO;

    public Integer getPointsAssignedByUserToTheTerm(Person user, Term term) {
        StudentPointsPerTerm pointsPerTerm = studentPointsPerTermDAO.getByPersonAndTerm(user, term);
        if (pointsPerTerm == null) {
            return 0;
        } else {
            return pointsPerTerm.getPoints();
        }
    }
}
