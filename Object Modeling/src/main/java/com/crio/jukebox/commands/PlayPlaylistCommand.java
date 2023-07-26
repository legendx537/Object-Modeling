package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand{
    private final IPlaylistService playlistService;

    public PlayPlaylistCommand(IPlaylistService playlistService) {
          this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
       String userId = tokens.get(1);
       String playlistId = tokens.get(2);

       try {
         Playlist playlist = playlistService.playPlaylist( userId , playlistId) ;
         
         Song currSong = playlist.getCurrSong();

         List<String> artistList = currSong.getArtistList();
         String artistListString = "";

         for(int i=0; i < artistList.size(); i++) {
             artistListString += artistList.get(i) + ",";
         }

         artistListString = artistListString.substring(0, artistListString.length() - 1);

         System.out.println( "Current Song Playing\n" +
                            "Song - " +  currSong.getSongName() + "\n" +
                            "Album - " + currSong.getAlbumName() + "\n" +
                            "Artists - " + artistListString
                            );
       } catch (RuntimeException e) {
        System.out.println("Error Message :- " + e.getMessage());
      }

      // PLAY-PLAYLIST 1- userId 1 - PlaylistId
      /*
       * "Current Song Playing\n"+
        "Song - South of the Border\n"+
        "Album - No.6 Collaborations Project\n"+
        "Artists - Ed Sheeran,Cardi.B,Camilla Cabello\n"+
       */
       
       
    }
}
