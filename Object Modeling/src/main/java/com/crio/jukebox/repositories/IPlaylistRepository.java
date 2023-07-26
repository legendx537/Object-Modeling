package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlaylistRepository extends CRUDRepository<Playlist , String>{
    Playlist playPlaylist(Playlist playlist);
    Playlist addSongToPlaylist(Playlist playlist, List<Song> songList);
    Playlist deleteSongFromPlaylist(Playlist playlist, List<Song> songList);
    Song nextSong();
    Song previousSong();
    Song playSongNumber(Song song);
}
