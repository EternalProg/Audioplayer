package com.audioplayer.api.model;

import com.audioplayer.api.DTO.PlaylistCreateDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "playlists", uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "user_id"})})
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // Плейлист належить одному користувачу: Тут поле user вказує на користувача, якому належить плейлист.
    // У базі даних це реалізується через зовнішній ключ user_id у таблиці playlist.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Плейлист може містити декілька аудіофайлів, і кожен аудіофайл може бути в багатьох плейлистах:
    @OneToMany(mappedBy = "playlist", orphanRemoval = true)
    private List<PlaylistAudiofile> playlistAudiofiles;

    public Playlist(PlaylistCreateDTO playlistCreateDTO) {
        setAll(playlistCreateDTO);
    }

    public void setAll(PlaylistCreateDTO playlistCreateDTO) {
        this.title = playlistCreateDTO.getTitle();
    }
}
