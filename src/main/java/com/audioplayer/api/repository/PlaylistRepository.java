package com.audioplayer.api.repository;

import com.audioplayer.api.model.Playlist;
import com.audioplayer.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends PagingAndSortingRepository<Playlist, Long> {
    Page<Playlist> findByUser(User user, Pageable pageable);

    Page<Playlist> findByName(String name, Pageable pageable);
}
