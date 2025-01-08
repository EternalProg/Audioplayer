package com.audioplayer.api.model;

import jakarta.persistence.*;

import java.util.List;


// Тобто мені потрібно зберігати в плейлистах айді з усіма треками які пренадлежть їй.
// Або в аудіофайлах, всі плейлисти в які цей файл входить
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Плейлист належить одному користувачу: Тут поле user вказує на користувача, якому належить плейлист.
    // У базі даних це реалізується через зовнішній ключ user_id у таблиці playlist.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // Плейлист може містити декілька аудіофайлів, і кожен аудіофайл може бути в багатьох плейлистах:
    @OneToMany(mappedBy = "playlist")
    private List<PlaylistAudiofile> playlistAudiofiles;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
