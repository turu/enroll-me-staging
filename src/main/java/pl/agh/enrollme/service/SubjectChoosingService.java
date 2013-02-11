package pl.agh.enrollme.service;

import org.springframework.stereotype.Service;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.model.Teacher;
import pl.agh.enrollme.utils.Color;
import pl.agh.enrollme.utils.DayOfWeek;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Service
public class SubjectChoosingService implements ISubjectChoosingService {

    private Subject[] choosenSubjects;

    @Override
    public List<Subject> getAvailableSubjectForEnrollment(Enroll enroll) {
        Teacher teacher1 = new Teacher("dr", "Stanisław", "Sobieszko", "4.11");
        Teacher teacher2 = new Teacher("dr", "Stasio", "Mieszko", "4.11");
        Subject subject1 = new Subject(enroll, null, "Analiza", 1, Color.GREEN, "4.33", teacher1, DayOfWeek.MONDAY,
                null, null);
        Subject subject2 = new Subject(enroll, null, "Fizyka", 1, Color.RED, "4.11", teacher2, DayOfWeek.FRIDAY,
                null, null);
        List<Subject> subjects = new ArrayList<Subject>(2);
        subjects.add(subject1);
        subjects.add(subject2);
        return subjects;
    }

    public void setChoosenSubjects(Subject[] choosenSubjects) {
        this.choosenSubjects = choosenSubjects;
    }

    public Subject[] getChoosenSubjects() {
        return choosenSubjects;
    }
}
