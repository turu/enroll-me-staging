package pl.agh.enrollme.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.enrollme.model.Person;

@Repository
public class PersonDAO implements IPersonDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void addPerson(Person person) {
        em.persist(person);
    }

    @Transactional
    public List<Person> listPeople() {
        CriteriaQuery<Person> c = em.getCriteriaBuilder().createQuery(Person.class);
        Root<Person> from = c.from(Person.class);
        c.orderBy(em.getCriteriaBuilder().desc(from.get("lastName")));

        return em.createQuery(c).getResultList();
    }

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
        CriteriaQuery<Person> c = em.getCriteriaBuilder().createQuery(Person.class);
        return em.createQuery(c).getSingleResult();
    }
}
