package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IPlaylistService;

public class PlaySongCommand implements ICommand {
    private final IPlaylistService playlistService;

    public PlaySongCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {

        String command = tokens.get(2);
        String userId = tokens.get(1);

        try {
            if (command.equals("NEXT")) {
                Song song = playlistService.nextSong(userId);

                List<String> artistList = song.getArtistList();
                String artistListString = "";
                for(int i=0; i < artistList.size(); i++) {
                    artistListString += artistList.get(i) + ",";
                }
                artistListString = artistListString.substring(0, artistListString.length() - 1);

                System.out.println("Current Song Playing\n" + "Song - " + song.getSongName() + "\n"
                        + "Album - " + song.getAlbumName() + "\n" + "Artists - "
                        + artistListString);
            } else if (command.equals("BACK")) {
                Song song = playlistService.previousSong(userId);

                List<String> artistList = song.getArtistList();
                String artistListString = "";
                for(int i=0; i < artistList.size(); i++) {
                    artistListString += artistList.get(i) + ",";
                }
                artistListString = artistListString.substring(0, artistListString.length() - 1);

                System.out.println("Current Song Playing\n" + "Song - " + song.getSongName() + "\n"
                        + "Album - " + song.getAlbumName() + "\n" + "Artists - "
                        + artistListString);
            } else {
                Song song = playlistService.playSongNumber( userId ,command);

                List<String> artistList = song.getArtistList();
                String artistListString = "";
                for(int i=0; i < artistList.size(); i++) {
                    artistListString += artistList.get(i) + ",";
                }
       
                artistListString = artistListString.substring(0, artistListString.length() - 1);

                System.out.println("Current Song Playing\n" + "Song - " + song.getSongName() + "\n"
                        + "Album - " + song.getAlbumName() + "\n" + "Artists - "
                        + artistListString);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * "Current Song Playing\n"+ "Song - Cross Me\n"+ "Album - No.6 Collaborations Project\n"+
     * "Artists - Ed Sheeran,Chance The Rapper,PnB Rock\n"+
     */
}
