package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class PlaylistService implements IPlaylistService {
        private final ISongRepository songRepository;
        private final IUserRepository userRepository;
        private final IPlaylistRepository playlistRepository;

        public PlaylistService(ISongRepository songRepository, IUserRepository userRepository,
                        IPlaylistRepository playlistRepository) {
                this.songRepository = songRepository;
                this.userRepository = userRepository;
                this.playlistRepository = playlistRepository;
        }

        @Override
        public Playlist createPlayList(String userId, String playlistName, List<String> songListId)
                        throws UserNotFoundException, SongNotFoundException {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException(
                                                "User with userId" + userId + "does not exist!"));
                List<Song> songList = new ArrayList<>();

                for (String songId : songListId) {
                        Song song = songRepository.findById(songId)
                                        .orElseThrow(() -> new SongNotFoundException(
                                                        "Some Requested Songs Not Available. Please try again"));
                        songList.add(song);
                }

                Playlist playlist = new Playlist(user.getId(), playlistName, songList);
                return playlistRepository.save(playlist);
        }

        @Override
        public String deletePlaylist(String userId, String playlistId) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException(
                                                "User with userId" + userId + "does not exist!"));
                Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(
                                () -> new PlaylistNotFoundException("Playlist Not Found"));
                playlistRepository.deleteById(playlistId);
                return "Delete Successful";
        }

        @Override
        public Playlist playPlaylist(String userId, String playlistId) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException(
                                                "User with userId" + userId + "does not exist!"));
                Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(
                                () -> new PlaylistNotFoundException("Playlist Not Found"));

                if (playlist.getSongList() == null || playlist.getSongList().size() == 0) {
                        throw new RuntimeException("Playlist is empty.");
                }

                return playlistRepository.playPlaylist(playlist);
        }

        @Override
        public Playlist deleteSongFromPlaylist(String userId, String playlistId,
                        List<String> songIds) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException(
                                                "User with userId" + userId + "does not exist!"));
                Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(
                                () -> new PlaylistNotFoundException("Playlist Not Found"));

                List<Song> songList = new ArrayList<>();

                for (String songId : songIds) {
                        Song song = songRepository.findById(songId)
                                        .orElseThrow(() -> new SongNotFoundException(
                                                        "Some Requested Songs Not Available. Please try again"));
                        // if Song is not present for deletion throw runtime exception
                        if (!checkIfSongAlreadyPresentInPlaylist(playlist, song))
                                throw new RuntimeException(
                                                "Some Requested Songs for Deletion are not present in the playlist. Please try again.");

                        songList.add(song);
                }

                return playlistRepository.deleteSongFromPlaylist(playlist, songList);
        }

        // check if song is present in the playlist or not
        private boolean checkIfSongAlreadyPresentInPlaylist(Playlist playlist, Song songFound) {
                List<Song> songs = playlist.getSongList();
                for (Song song : songs) {
                        if (song == songFound)
                                return true;
                }
                return false;
        }

        @Override
        public Playlist addSongToPlaylist(String userId, String playlistId, List<String> songIds) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException(
                                                "User with userId" + userId + "does not exist!"));
                Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(
                                () -> new PlaylistNotFoundException("Playlist Not Found"));

                List<Song> songList = new ArrayList<>();

                for (String songId : songIds) {
                        Song song = songRepository.findById(songId)
                                        .orElseThrow(() -> new SongNotFoundException(
                                                        "Some Requested Songs Not Available. Please try again"));
                        songList.add(song);
                }

                return playlistRepository.addSongToPlaylist(playlist, songList);
        }

        @Override
        public Song nextSong(String userId) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException(
                                                "User with userId" + userId + "does not exist!"));

                return playlistRepository.nextSong();
        }

        @Override
        public Song previousSong(String userId) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException(
                                                "User with userId" + userId + "does not exist!"));
                return playlistRepository.previousSong();
        }

        @Override
        public Song playSongNumber(String userId, String command2) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException(
                                                "User with userId" + userId + "does not exist!"));
                Song song = songRepository.findById(command2)
                                .orElseThrow(() -> new SongNotFoundException(
                                                "Some Requested Songs Not Available. Please try again"));
                                                          
                return playlistRepository.playSongNumber(song);
        }



}
