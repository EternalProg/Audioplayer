package com.audioplayer.api.DTO;

import com.audioplayer.api.model.Playlist;
import lombok.Data;

@Data
public class PlaylistDTO {
    private Long id;
    private String title;

    public PlaylistDTO(Playlist playlist) {
        this.id = playlist.getId();
        this.title = playlist.getTitle();
    }
}
