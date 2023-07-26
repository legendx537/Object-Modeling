package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;

public interface IPlaylistService {
    Playlist createPlayList(String userId, String playlistName, List<String> songList) throws UserNotFoundException, SongNotFoundException;

    String deletePlaylist(String userId, String playlistId);

    Playlist playPlaylist(String userId, String playlistId);

    Playlist deleteSongFromPlaylist(String userId, String playlistId, List<String> songIds);

    Playlist addSongToPlaylist(String userId, String playlistId, List<String> songIds);

    Song nextSong(String userId);

    Song previousSong(String userId);

    Song playSongNumber(String command, String command2);
}
