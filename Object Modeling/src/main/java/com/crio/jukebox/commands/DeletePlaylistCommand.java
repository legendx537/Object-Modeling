package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand{
    private final IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService) {
          this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
       String userId = tokens.get(1);
       String playlistId = tokens.get(2);

       try {
        System.out.println( playlistService.deletePlaylist( userId , playlistId) );
       } catch (PlaylistNotFoundException e) {
        System.out.println("Error Message :- " +e.getMessage());
      }
       
       
    }
    // command format :- DELETE-PLAYLIST 1 2
    //expected output 
}
