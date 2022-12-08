package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional <Person> findByFio(String fio);
}
