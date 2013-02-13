package pl.agh.enrollme.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.enrollme.model.Person;

@Repository
public class PersonDAO implements IPersonDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAO.class);

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void addPerson(Person person) {
        LOGGER.debug("Adding person " + person.getFirstName() + " " + person.getLastName());
        em.persist(person);
    }

    @Transactional
    public void updatePerson(Person person) {
        em.merge(person);
    }

    @Transactional
    public List<Person> listPeople() {
        CriteriaQuery<Person> c = em.getCriteriaBuilder().createQuery(Person.class);
        Root<Person> from = c.from(Person.class);
        c.orderBy(em.getCriteriaBuilder().desc(from.get("lastName")));

        return em.createQuery(c).getResultList();
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
    public void removePerson(Integer id) {
        Person person = em.find(Person.class, id);
        if (null != person) {
            em.remove(person);
        }
    }

    @Transactional
    public Person getPerson(Integer id) {
        LOGGER.debug("Getting person with id " + id);
        return em.find(Person.class, id);
    }
}
