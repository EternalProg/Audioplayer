package com.audioplayer.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class PlaylistAudiofile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // первинний ключ

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "audiofile_id", nullable = false)
    private Audiofile audiofile;
}
