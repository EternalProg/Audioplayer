package com.audioplayer.api.model;

import com.audioplayer.api.DTO.AudiofileCreateDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Audiofile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

    private String artist;

    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Кожен аудіофайл пов’язаний з конкретним користувачем
    private User user;

    @OneToMany(mappedBy = "audiofile", orphanRemoval = true)
    private List<PlaylistAudiofile> playlistAudiofiles;

    public Audiofile(AudiofileCreateDTO audiofileCreateDTO) {
        setAll(audiofileCreateDTO);
    }

    public void setAll(AudiofileCreateDTO audiofileCreateDTO) {
        this.title = audiofileCreateDTO.getTitle();
        this.artist = audiofileCreateDTO.getArtist();
        this.duration = audiofileCreateDTO.getDuration();
    }
}
