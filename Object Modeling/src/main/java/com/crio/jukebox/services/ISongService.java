package com.crio.jukebox.services;

import java.io.IOException;

public interface ISongService {
    String loadSongsFromCsvToSongRepositories(String csv) throws IOException;
}
