//package com.cookiebros.libmvc.services;
//
//import com.cookiebros.libmvc.models.Person;
//import com.cookiebros.libmvc.repositories.PeopleRepository;
//import com.cookiebros.libmvc.security.PersonDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class PersonDetailsServiceImpl implements PersonDetailsService{
//    private final PeopleRepository peopleRepository;
//
//    @Autowired
//    public PersonDetailsServiceImpl(PeopleRepository peopleRepository) {
//        this.peopleRepository = peopleRepository;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Person> user = peopleRepository.findByEmail(username);
//        if (user.isEmpty())
//            throw new UsernameNotFoundException("Пользователь с таким email не найден");
//        return new PersonDetails(user.get());
//    }
//}
