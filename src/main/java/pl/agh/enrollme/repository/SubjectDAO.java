package pl.agh.enrollme.repository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import pl.agh.enrollme.model.Subject;

/**
 * @author Michal Partyka
 */
@Repository
public class SubjectDAO implements ISubjectDAO {

    @Override
    public void fillCurrentUserSubjectList(Subject[] subjects) {
        //TODO: fill the subjects into databse under currentUser. (for subjects, user.addSubject())...
        System.out.println(( (UserDetails) SecurityContextHolder.getContext().getAuthentication().
                getPrincipal()).getUsername());
    }
}
