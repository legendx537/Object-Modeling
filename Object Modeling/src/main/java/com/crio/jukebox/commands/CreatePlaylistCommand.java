package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand {
    private final IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistName = tokens.get(2);
        List<String> songListId = new ArrayList<>();

        for (int i = 3; i < tokens.size(); i++) {
            songListId.add(tokens.get(i));
        }

        try {
            Playlist playlist = playlistService.createPlayList(userId, playlistName, songListId);
            System.out.println("Playlist ID - " + playlist.getId() );
        } catch (UserNotFoundException | SongNotFoundException e) {
            System.out.println("Error Occured : -" + e.getMessage() );
        }

        // "Playlist ID - 1 - expected output 

    }
}
