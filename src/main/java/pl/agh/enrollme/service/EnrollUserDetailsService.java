package pl.agh.enrollme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.repository.IPersonDAO;

/**
 * Author: Piotr Turek
 */
@Service
public class EnrollUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnrollUserDetailsService.class);

    @Autowired
    private IPersonDAO personDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Person person = personDAO.findByUsername(username);

        if (person == null) {
            LOGGER.info("User " + username + " could not be found in a data source.");
            throw new UsernameNotFoundException("User " + username + " could not be found in a data source.");
        }

        UserDetails userDetails = (UserDetails) person;

        if (userDetails.getAuthorities().isEmpty()) {
            LOGGER.info("User " + username + " has no granted authority.");
            throw new UsernameNotFoundException("User " + username + " has no granted authority.");
        }

        LOGGER.info("User " + username + " successfully retrieved from data source.");

        return userDetails;
    }

}
