package pl.agh.enrollme.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;
import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.model.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PersonDAO extends GenericDAO<Person> implements IPersonDAO {

    @Autowired
    private IEnrollmentDAO enrollmentDAO;

    @PersistenceContext
    private EntityManager em;

    public PersonDAO() {
        super(Person.class);
    }

    @Transactional
    @Override
    public Person findByUsername(String username) {
        final TypedQuery<Person> query = em.createQuery("Select p from Person p where p.username = :username",
                Person.class).setParameter("username", username);
        final List<Person> resultList = query.getResultList();

        if (resultList.size() > 1) {
           throw new IllegalStateException("User " + username + " is not unique in data source!");
        }

        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    @Transactional
    @Override
    public List<Person> getPeopleWhoSavedPreferencesForCustomEnrollment(Enroll enrollment) {
        Enroll enrollmentFromDB = enrollmentDAO.getByPK(enrollment.getEnrollID());
        List<Person> people = enrollmentFromDB.getPersons();
        for (Person person: people) {
            if (person.getSubjectsSaved().isEmpty()) {
                people.remove(person);
            }
        }
        return people;
    }

    @Override
    public List<Subject> getSavedSubjects(Person person) {
        Person obtainedFromDB = getByPK(person.getId());
        return obtainedFromDB.getSubjectsSaved();
    }
}
