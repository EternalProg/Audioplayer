package com.audioplayer.api.repository;

import com.audioplayer.api.DTO.AudiofileDTO;
import com.audioplayer.api.model.Audiofile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AudiofileRepository extends PagingAndSortingRepository<Audiofile, Long>, JpaRepository<Audiofile, Long> {
    Page<Audiofile> findByTitleAndUserId(String title, Long userId, Pageable pageable);

    Page<Audiofile> findAudiofilesByUserId(Long userId, Pageable pageable);

    @Query("SELECT pa.audiofile FROM PlaylistAudiofile pa WHERE pa.playlist.id = :playlistId")
    Page<Audiofile> findAudiofilesByPlaylistId(@Param("playlistId") Long playlistId, Pageable pageable);
}
