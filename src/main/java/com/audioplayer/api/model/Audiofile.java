package com.audioplayer.api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Audiofile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;
    private Integer duration;

    @OneToMany(mappedBy = "audiofile")
    private List<PlaylistAudiofile> playlistAudiofiles;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
