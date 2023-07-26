package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.PlaylistStatus;
import com.crio.jukebox.entities.Song;

public class PlaylistRepository implements IPlaylistRepository {

    private final Map<Integer, Playlist> playListMap;
    private Integer autoIncrement = 0;

    private Playlist loadedPlaylist;
    private List<Song> loadedSongList;

    public PlaylistRepository() {
        playListMap = new HashMap<Integer, Playlist>();
    }

    public PlaylistRepository(Map<Integer, Playlist> playListMap) {
        this.playListMap = playListMap;
        autoIncrement = playListMap.size();
    }

    // Below are the Curd Interface methods
    @Override
    public Playlist save(Playlist entity) {
        if (entity.getId() == null) {
            autoIncrement++;
            Playlist newPlaylist = new Playlist(autoIncrement, entity.getUserId(),
                    entity.getPalylistName(), entity.getSongList());
            playListMap.put(autoIncrement, newPlaylist);
            return newPlaylist;
        }
        playListMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Playlist> findById(String id) {
        Integer playlistId = Integer.parseInt(id);
        return Optional.ofNullable(playListMap.get(playlistId));
    }

    @Override
    public Playlist playPlaylist(Playlist playlist) {
        loadedPlaylist = playlist;
        loadedSongList = playlist.getSongList();

        playlist.setPlaylistStatus(PlaylistStatus.PLAYING);
        playlist.setCurrSong(loadedSongList.get(0));
        return loadedPlaylist;
    }

    @Override
    public Playlist addSongToPlaylist(Playlist playlist, List<Song> loadedSongList) {

        List<Song> songs = playlist.getSongList();
        for (Song song : loadedSongList) {
            if (!checkIfSongAlreadyPresentInPlaylist(playlist, song))
                songs.add(song);
        }
        playlist.setSongList(songs);

        return playlist;
    }

    private boolean checkIfSongAlreadyPresentInPlaylist(Playlist playlist, Song songFound) {
        List<Song> songs = playlist.getSongList();
        for (Song song : songs) {
            if (song == songFound)
                return true;
        }
        return false;
    }

    @Override
    public Playlist deleteSongFromPlaylist(Playlist playlist, List<Song> loadedSongList) {
        List<Song> songs = playlist.getSongList();
        for (Song song : loadedSongList) {
            if (checkIfSongAlreadyPresentInPlaylist(playlist, song))
                songs.remove(song);
        }
        playlist.setSongList(songs);

        return playlist;
    }

    @Override
    public Song nextSong() {

        Song currentSong = loadedPlaylist.getCurrSong();
        int index = loadedSongList.indexOf(currentSong);
        if (index == loadedSongList.size() - 1) {
            loadedPlaylist.setCurrSong(loadedSongList.get(0));
            return loadedPlaylist.getCurrSong();
        }

        loadedPlaylist.setCurrSong(loadedSongList.get(index + 1));
        return loadedPlaylist.getCurrSong();
    }

    @Override
    public Song previousSong() {
        Song currentSong = loadedPlaylist.getCurrSong();
        int index = loadedSongList.indexOf(currentSong);

        if (index == 0) {
            loadedPlaylist.setCurrSong(loadedSongList.get(loadedSongList.size() - 1));
            return loadedPlaylist.getCurrSong();
        }

        loadedPlaylist.setCurrSong(loadedSongList.get(index - 1));
        return loadedPlaylist.getCurrSong();
    }

    @Override
    public Song playSongNumber(Song song) {
        // Check if the Song is a part of loaded playlist or not
        if (!checkIfSongAlreadyPresentInPlaylist(loadedPlaylist, song))
            throw new RuntimeException("Given song id is not a part of the active playlist");

        loadedPlaylist.setCurrSong(song);

        return song;
    }


    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Playlist> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(String id) {
        Integer playlistId = Integer.parseInt(id);
        playListMap.remove(playlistId);
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }
}
