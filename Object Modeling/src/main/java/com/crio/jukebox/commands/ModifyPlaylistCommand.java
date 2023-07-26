package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand {
    private final IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String command = tokens.get(1);
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);
        List<String> songIds = new ArrayList<>();

        for (int i = 4; i < tokens.size(); i++) {
            songIds.add(tokens.get(i));
        }

        try {
            if (command.equals("ADD-SONG")) {
                Playlist modifiedPlaylist =
                        playlistService.addSongToPlaylist(userId, playlistId, songIds);

                List<Song> songList = modifiedPlaylist.getSongList();
                String songIdsList = "";

                for (Song song : songList) {
                    songIdsList += song.getId() + " ";
                }

                songIdsList = songIdsList.substring(0, songIdsList.length() - 1);

                System.out.println("Playlist ID - " + modifiedPlaylist.getId() + "\n"
                        + "Playlist Name - " + modifiedPlaylist.getPalylistName() + "\n"
                        + "Song IDs - " + songIdsList);
            } else {
                Playlist modifiedPlaylist =
                        playlistService.deleteSongFromPlaylist(userId, playlistId, songIds);

                List<Song> songList = modifiedPlaylist.getSongList();
                String songIdsList = "";

                for (Song song : songList) {
                    songIdsList += song.getId() + " ";
                }

                songIdsList = songIdsList.substring(0, songIdsList.length() - 1);
                
                System.out.println("Playlist ID - " + modifiedPlaylist.getId() + "\n"
                        + "Playlist Name - " + modifiedPlaylist.getPalylistName() + "\n"
                        + "Song IDs - " + songIdsList);
            }
        } catch (RuntimeException e) {
            System.out.println("Error Message :- " + e.getMessage());
        }
    }

    /*
     * Playlist ID - 1 Playlist Name - MY_PLAYLIST_1 Song IDs - 1 2 3 4 5 6 7
     * 
     * 
     * 
     */
}
