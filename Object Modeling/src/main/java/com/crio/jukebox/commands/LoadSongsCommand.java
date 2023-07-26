package com.crio.jukebox.commands;

import java.io.IOException;
import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadSongsCommand implements ICommand {

    private final ISongService songService;

    public LoadSongsCommand(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String csv = tokens.get(1);

        try {
            System.out.println(songService.loadSongsFromCsvToSongRepositories(csv));
        } catch (IOException e) {
            System.out.println("Error Occured :- " + e.getMessage());
        }

    }

}
