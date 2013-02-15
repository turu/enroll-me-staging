package pl.agh.enrollme.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Enroll;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface IEnrollmentDAO {
    @Transactional
    void update(Enroll toUpdate);

    @Transactional
    List<Enroll> getList();

    @Transactional
    <K> void add(Enroll add);

    @Transactional
    void remove(Enroll remove);

    @Transactional
    Enroll getByPK(Object PK);
}
