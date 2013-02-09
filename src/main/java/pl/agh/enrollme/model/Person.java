package pl.agh.enrollme.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

/**
 *  Author: Piotr Turek
 */
@Entity
public class Person implements Serializable, UserDetails {

    @Transient
    private static final long serialVersionUID = -5777367229609230476L;

    @Transient
    private final List<GrantedAuthority> authorityList = new ArrayList<>();

    @Id
    @GeneratedValue
    private Integer id = 0;

    @Column(unique = true)
    private String username = "";

    private String password = "";

    private String firstName = "";

    private String lastName = "";

    private Boolean accountNonExpired = false;

    private Boolean accountNonLocked = false;

    private Boolean credentialsNonExpired = false;

    private Boolean enabled = false;

    private String rolesToken = "";


    public Person() {
    }

    //TODO: adjust constructor to new fields or remove it
    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRolesToken() {
        return rolesToken;
    }

    public void setRolesToken(String roles) {
        this.rolesToken = roles;
        updateAuthorityList(roles);
    }

    private void updateAuthorityList(String roles) {
        final String[] split = roles.split("[.,;:|]+");
        authorityList.clear();
        for (String token : split) {
            authorityList.add(new SimpleGrantedAuthority(token.trim().toUpperCase()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!username.equals(person.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
