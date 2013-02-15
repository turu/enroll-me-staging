package pl.agh.enrollme.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Person;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Michal Partyka
 */
public class GenericDAO<T> implements IGenericDAO<T> {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private Class<T> type;

    public GenericDAO(Class<T> type) {
        if(type.getAnnotation(Entity.class) == null) {
            throw new IllegalArgumentException("DAO can be created only with class annotated by Entity. Class " +
            type.getName() + " unfortunetly has no Entity annotation");
        }
        this.type = type;
    }

    @Transactional
    @Override
    public void update(T toUpdate) {
        em.merge(toUpdate);
    }

    @Transactional
    @Override
    public void add(T add) {
        if(em.find(type, entityManagerFactory.getPersistenceUnitUtil().getIdentifier(add)) != null) {
            em.merge(add);
            return;
        }
            em.persist(add);
    }

    @Override
    @Transactional
    public void remove(T remove) {
        T removeThis = em.find(type, entityManagerFactory.getPersistenceUnitUtil().getIdentifier(remove));
        if(removeThis != null) {
            em.remove(removeThis);
        } else {
            throw new IllegalStateException("There is no entity you want to remove in database");
        }
    }

    //TODO: must be transactional ?? ~ partyks
    @Transactional
    @Override
    public List<T> getList() {
        CriteriaQuery<T> c = em.getCriteriaBuilder().createQuery(type);
        Root<T> from = c.from(type);
        c.select(from);
        return em.createQuery(c).getResultList();
    }

    @Transactional
    @Override
    public T getByPK(Object PK) {
        System.out.println("[partyks DEBUG] I have returned some object by PK");
        return em.find(type, PK);
    }

    @Transactional
    @Override
    public void removeByPK(Object PK) {
        T removeThis = em.find(type, PK);
        if (removeThis != null) {
            em.remove(removeThis);
        }
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

}
