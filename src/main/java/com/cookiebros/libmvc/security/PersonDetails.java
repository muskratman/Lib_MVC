//package com.cookiebros.libmvc.security;
//
//import com.cookiebros.libmvc.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//public class PersonDetails implements UserDetails {
//    private final Person person;
//
//    public PersonDetails(Person person) {
//        this.person = person;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//    @Override
//    public String getPassword() {
//        return this.person.getPassword();
//    }
//    @Override
//    public String getUsername() {
//        return this.person.getEmail();
//    }
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
