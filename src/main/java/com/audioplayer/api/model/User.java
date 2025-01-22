package com.audioplayer.api.model;

import com.audioplayer.api.DTO.UserCreateDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = false)
    private String password;

    // mappedBy = "user" вказує, що зв’язок контролюється полем user у класі Playlist.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Playlist> playlists;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Вказуємо, що це зворотний зв’язок
    private Set<Audiofile> audiofiles;

    public User(UserCreateDTO userDTO) {
        setAll(userDTO);
    }

    public void setAll(UserCreateDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
    }
}
