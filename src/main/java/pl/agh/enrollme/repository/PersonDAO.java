package pl.agh.enrollme.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import pl.agh.enrollme.model.Person;

@Repository
public class PersonDAO implements IPersonDAO {

    public static final Logger LOGGER = LoggerFactory.getLogger(PersonDAO.class.getName());

	@PersistenceContext
    EntityManager em;
    
    @Transactional
    public void addPerson(Person person) {
        LOGGER.info("Adding person " + person.getFirstName() + " " + person.getLastName());
        em.persist(person);
    }

    @Transactional
    public void updatePerson(Person person) {
        LOGGER.info("Changing person into - "+ person.getId() +
                " " + person.getFirstName() + " " + person.getLastName());

        em.merge(person);
    }

    @Transactional
    public List<Person> listPeople() {
        LOGGER.info("Retrieving list of people in database");
        CriteriaQuery<Person> c = em.getCriteriaBuilder().createQuery(Person.class);
        Root<Person> from = c.from(Person.class);
        c.orderBy(em.getCriteriaBuilder().desc(from.get("lastName")));

        return em.createQuery(c).getResultList();
    }

    @Transactional
    public void removePerson(Integer id) {
        LOGGER.info("Removing person with id " + id);
        Person person = em.find(Person.class, id);
        if (null != person) {
            LOGGER.error("Person not found - " + id);
            em.remove(person);
        }
    }

    @Transactional
    public Person getPerson(Integer id) {
        LOGGER.info("Retrieving person with id " + id);
        return em.find(Person.class, id);
    }

}
