package com.cookiebros.libmvc.repositories;

import com.cookiebros.libmvc.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishersRepository extends JpaRepository<Publisher, Integer> {
}
