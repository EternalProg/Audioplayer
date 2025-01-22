package com.audioplayer.api.DTO;

import com.audioplayer.api.model.Audiofile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AudiofileDTO {
    private Long id;
    private String title;
    private String artist;
    private Integer duration;

    public AudiofileDTO(Audiofile audiofile) {
        this.id = audiofile.getId();
        this.title = audiofile.getTitle();
        this.artist = audiofile.getArtist();
        this.duration = audiofile.getDuration();
    }
}