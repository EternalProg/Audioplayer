package com.audioplayer.api.repository;

import com.audioplayer.api.model.Audiofile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface AudiofileRepository extends PagingAndSortingRepository<Audiofile, Long> {
    Optional<Audiofile> findByTitle(String title);

    Page<Audiofile> findByArtist(String artist, Pageable pageable);
}
