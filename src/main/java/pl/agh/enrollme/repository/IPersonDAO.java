package pl.agh.enrollme.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.model.Subject;

import java.util.List;

public interface IPersonDAO {

    void add(Person person);
    Person update(Person person);
    void removeByPK(Object id);
    void remove(Person person);
    Person getByPK(Object id);
    List<Person> getList();
    Person findByUsername(String username);

    @Transactional
    List<Person> getPeopleWhoSavedPreferencesForCustomEnrollment(Enroll enrollment);
    @Transactional
    List<Subject> getSavedSubjects(Person person);
    Person getCurrentUser();
}
