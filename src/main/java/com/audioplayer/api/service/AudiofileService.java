package com.audioplayer.api.service;

import com.audioplayer.api.DTO.AudiofileCreateDTO;
import com.audioplayer.api.DTO.AudiofileDTO;
import com.audioplayer.api.model.Audiofile;
import com.audioplayer.api.model.Playlist;
import com.audioplayer.api.model.PlaylistAudiofile;
import com.audioplayer.api.model.User;
import com.audioplayer.api.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AudiofileService {
    private final AudiofileRepository audiofileRepository;
    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;
    private final PlaylistAudiofileRepository playlistAudiofileRepository;

    public AudiofileService(AudiofileRepository audiofileRepository, UserRepository userRepository, PlaylistRepository playlistRepository, PlaylistAudiofileRepository playlistAudiofileRepository) {
        this.audiofileRepository = audiofileRepository;
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.playlistAudiofileRepository = playlistAudiofileRepository;
    }

    // Read

    public Page<AudiofileDTO> getUserAudiofiles(Long userID, int page, int size) {
        Page<Audiofile> audiofilePage = audiofileRepository.findAudiofilesByUserId(userID, PageRequest.of(page, size));
        return audiofilePage.map(AudiofileDTO::new);
    }

    public Page<AudiofileDTO> getAudiofiles(String title, Long userId, int page, int size) {
        return audiofileRepository.findByTitleAndUserId(title, userId, PageRequest.of(page, size)).map(AudiofileDTO::new);
    }

    public Optional<AudiofileDTO> getAudiofileById(Long audiofileId) {
        return audiofileRepository.findById(audiofileId).map(AudiofileDTO::new);
    }

    public Page<AudiofileDTO> getAudiofilesByPlaylistId(Long playlistId, int page, int size) {
        return audiofileRepository.findAudiofilesByPlaylistId(playlistId, PageRequest.of(page, size)).map(AudiofileDTO::new);
    }

    public List<AudiofileDTO> getAllAudiofiles() {
        return audiofileRepository.findAll()
                .stream()
                .map(AudiofileDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<AudiofileDTO> saveAudiofileForUserAndPlaylist(Long userId, Long playlistId, AudiofileCreateDTO audiofileCreateDTO) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Playlist> playlistOpt = playlistRepository.findById(playlistId);

        if (userOpt.isPresent() && playlistOpt.isPresent()) {
            Audiofile audiofile = new Audiofile(audiofileCreateDTO);
            audiofile.setUser(userOpt.get());

            audiofile = audiofileRepository.save(audiofile);

            PlaylistAudiofile playlistAudiofile = new PlaylistAudiofile();
            playlistAudiofile.setAudiofile(audiofile);
            playlistAudiofile.setPlaylist(playlistOpt.get());

            playlistAudiofileRepository.save(playlistAudiofile);

            return Optional.of(new AudiofileDTO(audiofile));
        }
        return Optional.empty();
    }

    public Optional<AudiofileDTO> updateAudiofile(Long audiofileId, AudiofileCreateDTO audiofileCreateDTO) {
        Optional<Audiofile> audiofileOpt = audiofileRepository.findById(audiofileId);
        if (audiofileOpt.isPresent()) {
            Audiofile audiofile = audiofileOpt.get();
            audiofile.setAll(audiofileCreateDTO);
            return Optional.of(new AudiofileDTO(audiofileRepository.save(audiofile)));
        }
        return Optional.empty();
    }

    // Delete
    public boolean deleteAudiofile(Long audiofileId) {
        if (audiofileRepository.existsById(audiofileId)) {
            audiofileRepository.deleteById(audiofileId);
            return true;
        }
        return false;
    }
}
