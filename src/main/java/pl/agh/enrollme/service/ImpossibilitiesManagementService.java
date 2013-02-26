package pl.agh.enrollme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.StudentPointsPerTerm;
import pl.agh.enrollme.repository.IStudentPointsPerTermDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafał Cymerys
 */
@Service
public class ImpossibilitiesManagementService {

    @Autowired
    private IStudentPointsPerTermDAO pointsDAO;

    public List<StudentPointsPerTerm> getImpossibilitiesForEnroll(Enroll enroll) {
        List<StudentPointsPerTerm> result = new ArrayList<>();

        List<StudentPointsPerTerm> daoResult = pointsDAO.getList();

        for (StudentPointsPerTerm i : daoResult) {
            if ((i.getTerm().getSubject().getEnroll().equals(enroll)) &&
                    (i.getPoints().equals(-1)) && (!i.getAccepted())) {
                result.add(i);
            }
        }

        return result;
    }

    public void acceptImpossibility(Integer id) {
        StudentPointsPerTerm impossibility = pointsDAO.getByPK(id);

        impossibility.setAccepted(true);

        pointsDAO.update(impossibility);
    }

    public void declineImpossibility(Integer id) {
        StudentPointsPerTerm impossibility = pointsDAO.getByPK(id);

        impossibility.setPoints(0);

        pointsDAO.update(impossibility);
    }

}
