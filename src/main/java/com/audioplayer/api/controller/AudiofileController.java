package com.audioplayer.api.controller;

import com.audioplayer.api.DTO.AudiofileDTO;
import com.audioplayer.api.DTO.AudiofileCreateDTO;
import com.audioplayer.api.service.AudiofileService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/audiofiles")
public class AudiofileController {
    private final AudiofileService audiofileService;

    public AudiofileController(AudiofileService audiofileService) {
        this.audiofileService = audiofileService;
    }

    @GetMapping
    public ResponseEntity<List<AudiofileDTO>> getAllAudiofiles() {
        return ResponseEntity.ok(audiofileService.getAllAudiofiles());
    }

    @GetMapping("/by-playlistid/{playlistId}")
    public ResponseEntity<Page<AudiofileDTO>> getPlaylistAudiofiles(@PathVariable Long playlistId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(audiofileService.getAudiofilesByPlaylistId(playlistId, page, size));
    }

    @GetMapping("/by-userid/{userId}")
    public ResponseEntity<Page<AudiofileDTO>> getUserAudiofiles(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(audiofileService.getUserAudiofiles(userId, page, size));
    }

    @GetMapping("/{audiofileId}")
    public ResponseEntity<AudiofileDTO> getAudiofile(@PathVariable Long audiofileId) {
        Optional<AudiofileDTO> audiofile = audiofileService.getAudiofileById(audiofileId);
        return audiofile.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/{playlistId}")
    public ResponseEntity<AudiofileDTO> createAudiofile(@PathVariable Long userId, @PathVariable Long playlistId, @RequestBody AudiofileCreateDTO audiofileCreateDTO) {
        Optional<AudiofileDTO> AudiofileDTOOpt = audiofileService.saveAudiofileForUserAndPlaylist(userId, playlistId, audiofileCreateDTO);
        return AudiofileDTOOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{audiofileId}")
    public ResponseEntity<AudiofileDTO> updateAudiofile(@PathVariable Long audiofileId, @RequestBody AudiofileCreateDTO audiofileCreateDTO) {
        Optional<AudiofileDTO> AudiofileDTO = audiofileService.updateAudiofile(audiofileId, audiofileCreateDTO);
        return AudiofileDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{audiofileId}")
    public ResponseEntity<String> deleteAudiofile(@PathVariable Long audiofileId) {
        if (audiofileService.deleteAudiofile(audiofileId)) {
            return ResponseEntity.ok("Audiofile deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
