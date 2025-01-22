package com.audioplayer.api.DTO;

import com.audioplayer.api.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
