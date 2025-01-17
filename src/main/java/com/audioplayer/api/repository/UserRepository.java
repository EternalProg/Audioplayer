package com.audioplayer.api.repository;

import com.audioplayer.api.model.Audiofile;
import com.audioplayer.api.model.Playlist;
import com.audioplayer.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
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
