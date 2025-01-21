package com.audioplayer.api.controller;

import com.audioplayer.api.DTO.PlaylistCreateDTO;
import com.audioplayer.api.DTO.PlaylistDTO;
import com.audioplayer.api.service.PlaylistService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }

    @GetMapping("/by-userid/{userId}")
    public ResponseEntity<Page<PlaylistDTO>> getUserPlaylists(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(playlistService.getUserPlaylists(userId, page, size));
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<PlaylistDTO> getPlaylist(@PathVariable Long playlistId) {
        Optional<PlaylistDTO> playlist = playlistService.getPlaylistById(playlistId);
        return playlist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-title/{userId}/{title}")
    public ResponseEntity<PlaylistDTO> getPlaylist(@PathVariable String title, @PathVariable Long userId) {
        Optional<PlaylistDTO> playlist = playlistService.getPlaylist(title, userId);
        return playlist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PlaylistDTO> createPlaylistForUser(@PathVariable Long userId, @RequestBody PlaylistCreateDTO playlistCreateDTO) {
        Optional<PlaylistDTO> playlistDTOOpt = playlistService.savePlaylistForUser(userId, playlistCreateDTO);
        return playlistDTOOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{playlistId}")
    public ResponseEntity<PlaylistDTO> updatePlaylist(@PathVariable Long playlistId, @RequestBody PlaylistCreateDTO playlistCreateDTO) {
        Optional<PlaylistDTO> playlistDTO = playlistService.updatePlaylist(playlistId, playlistCreateDTO);
        return playlistDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long playlistId) {
        if (playlistService.deletePlaylist(playlistId)) {
            return ResponseEntity.ok("Playlist deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
