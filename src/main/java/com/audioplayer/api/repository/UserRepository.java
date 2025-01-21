package com.audioplayer.api.repository;

import com.audioplayer.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// CRUD  -> Create Read Update Delete
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Create
    //save() створений по дефолту

    // Read
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);


}
