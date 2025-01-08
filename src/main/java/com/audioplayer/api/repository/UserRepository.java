package com.audioplayer.api.repository;

import com.audioplayer.api.model.Playlist;
import com.audioplayer.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
