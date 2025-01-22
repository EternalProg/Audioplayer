package com.audioplayer.api.repository;

import com.audioplayer.api.model.PlaylistAudiofile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistAudiofileRepository extends JpaRepository<PlaylistAudiofile, Long> {

}
