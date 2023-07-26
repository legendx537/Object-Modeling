package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository {
    private final Map<Integer, Song> songMap;
    private Integer autoIncrement = 0;

    public SongRepository() {
        songMap = new HashMap<Integer, Song>();
    }

    public SongRepository(Map<Integer, Song> songMap) {
        this.songMap = songMap;
        this.autoIncrement = songMap.size();
    }

    @Override
    public Song save(Song song) {
        if (song.getId() == null) {
            autoIncrement++;
            Song newSong = new Song(autoIncrement, song.getSongName(), song.getGenere(),
                    song.getAlbumName(), song.getAlbumArtist(), song.getArtistList());
            songMap.put(autoIncrement, newSong);
            return newSong;
        }
        songMap.put(song.getId(), song);
        return song;
    }


    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Song> findById(String id) {
        Integer songId = Integer.parseInt(id);
        return Optional.ofNullable(songMap.get(songId));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

}
