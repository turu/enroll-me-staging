package pl.agh.enrollme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.repository.IPersonDAO;

/**
 * Author: Piotr Turek
 */
@Service
public class EnrollUserDetailsService implements UserDetailsService {

    @Autowired
    private IPersonDAO personDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

}
