package com.crio.jukebox.entities;

import java.util.List;
import java.util.Objects;

public class Song {

    private Integer id;
    private String songName;
    private String genere;
    private String albumName;
    private String albumArtist;
    private List<String> artistList;

    public Song(String songName, String genere, String albumName,String albumArtist,List<String> artistList) {
        this.songName = songName;
        this.genere = genere;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.artistList = artistList;
    }

    public Song(Integer id, String songName, String genere, String albumName,String albumArtist,
            List<String> artistList) {
        this(songName, genere, albumName, albumArtist , artistList);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }
    
    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<String> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<String> artistList) {
        this.artistList = artistList;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Song [albumName=" + albumName + ", artistList=" + artistList + ", genere=" + genere
                + ", id=" + id + ", songName=" + songName + "]";
    }

}
