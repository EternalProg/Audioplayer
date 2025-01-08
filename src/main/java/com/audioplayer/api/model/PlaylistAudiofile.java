package com.audioplayer.api.model;

import jakarta.persistence.*;

@Entity
public class PlaylistAudiofile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // первинний ключ

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "audiofile_id")
    private Audiofile audiofile;

    public Long getId() {
        return id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Audiofile getAudiofile() {
        return audiofile;
    }

    public void setAudiofile(Audiofile audiofile) {
        this.audiofile = audiofile;
    }
}
