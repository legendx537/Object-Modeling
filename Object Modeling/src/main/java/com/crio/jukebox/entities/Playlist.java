package com.crio.jukebox.entities;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private Integer id;
    private Integer userId;
    private String palylistName;
    private List<Song> songList;
    private PlaylistStatus playlistStatus;
    private Song currSong;

    public Playlist(Integer id, Integer userId, String playlistName, List<Song> songList) {
        this(userId, playlistName, songList);
        this.id = id;
        this.playlistStatus = PlaylistStatus.STOPPED;
        this.currSong = songList.get(0);
    }

    public Playlist(Integer userId, String playlistName, List<Song> songList) {
        this.userId = userId;
        this.palylistName = playlistName;
        this.songList = songList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPalylistName() {
        return palylistName;
    }

    public void setPalylistName(String palylistName) {
        this.palylistName = palylistName;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public PlaylistStatus getPlaylistStatus() {
        return playlistStatus;
    }

    public void setPlaylistStatus(PlaylistStatus playlistStatus) {
        this.playlistStatus = playlistStatus;
    }

    public Song getCurrSong() {
        return currSong;
    }

    public void setCurrSong(Song currSong) {
        this.currSong = currSong;
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
        Playlist other = (Playlist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Playlist [id=" + id + ", palylistName=" + palylistName + ", songList=" + songList
                + ", userId=" + userId + "]";
    }
}
