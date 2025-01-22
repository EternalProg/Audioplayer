package com.audioplayer.api.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AudiofileCreateDTO {
    private String title;
    private String artist;
    private Integer duration;
}