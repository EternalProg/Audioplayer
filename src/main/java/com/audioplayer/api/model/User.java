package com.audioplayer.api.model;

import com.audioplayer.api.DTO.UserCreateDTO;
import com.audioplayer.api.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private List<Playlist> playlists = new ArrayList<>();

    public User(UserCreateDTO userDTO) {
        setAll(userDTO);
    }

    public void setAll(UserCreateDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
    }
}
