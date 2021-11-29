package com.github.kononovit.serviceDataJPA.repository;

import com.github.kononovit.serviceDataJPA.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("FROM User u WHERE u.age BETWEEN ?1 AND ?2 ORDER BY u.age ASC")
    List<User> findByAgeRange(int from, int to);
}
