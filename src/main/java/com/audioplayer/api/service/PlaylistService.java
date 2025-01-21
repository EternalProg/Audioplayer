package com.audioplayer.api.service;

import com.audioplayer.api.DTO.PlaylistCreateDTO;
import com.audioplayer.api.DTO.PlaylistDTO;
import com.audioplayer.api.model.Playlist;
import com.audioplayer.api.model.User;
import com.audioplayer.api.repository.PlaylistRepository;
import com.audioplayer.api.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }

    // Read

    public Page<PlaylistDTO> getUserPlaylists(Long userID, int page, int size) {
        Page<Playlist> playlistPage = playlistRepository.findPlaylistsByUserId(userID, PageRequest.of(page, size));
        return playlistPage.map(PlaylistDTO::new);
    }

    public Optional<PlaylistDTO> getPlaylist(String title, Long userId) {
        return playlistRepository.findPlaylistByTitleAndUserId(title, userId).map(PlaylistDTO::new);
    }

    public Optional<PlaylistDTO> getPlaylistById(Long playlistId) {
        return playlistRepository.findPlaylistById(playlistId).map(PlaylistDTO::new);
    }

    public List<PlaylistDTO> getAllPlaylists() {
        return playlistRepository.findAll()
                .stream()
                .map(PlaylistDTO::new)
                .collect(Collectors.toList());
    }

    // Create

    public Optional<PlaylistDTO> savePlaylistForUser(Long userId, PlaylistCreateDTO playlistDTO) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            Playlist playlist = new Playlist(playlistDTO);
            playlist.setUser(userOpt.get());
            playlistRepository.save(playlist);
            return Optional.of(new PlaylistDTO(playlist));
        }
        return Optional.empty();
    }

    // Update
    public Optional<PlaylistDTO> updatePlaylist(Long playlistId, PlaylistCreateDTO playlistCreateDTO) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(playlistId);
        if (playlistOpt.isPresent()) {
            Playlist playlist = playlistOpt.get();
            playlist.setAll(playlistCreateDTO);
            return Optional.of(new PlaylistDTO(playlistRepository.save(playlist)));
        }
        return Optional.empty();
    }

    // Delete
    public boolean deletePlaylist(Long playlistId) {
        if (playlistRepository.existsById(playlistId)) {
            playlistRepository.deleteById(playlistId);
            return true;
        }
        return false;
    }
}
