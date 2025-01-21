package com.audioplayer.api.repository;

import com.audioplayer.api.model.Playlist;
import com.audioplayer.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends PagingAndSortingRepository<Playlist, Long>, JpaRepository<Playlist, Long> {
    Page<Playlist> findPlaylistsByUserId(Long UserId, Pageable pageable);

    Optional<Playlist> findPlaylistByTitleAndUserId(String title, Long userId);

    Optional<Playlist> findPlaylistById(Long playlistId);


}
