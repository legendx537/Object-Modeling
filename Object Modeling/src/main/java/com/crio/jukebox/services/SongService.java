package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class SongService implements ISongService {
    private final ISongRepository songRepository;

    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public String loadSongsFromCsvToSongRepositories(String csv) throws IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new FileReader(csv));
        String readLine = br.readLine();
        Song song, newSong;
        // String[] artistList;

        while (readLine != null) {
            String[] data = readLine.split(",");
             
             // // For Testing purpose 
            // artistList = data[5].split("#");
            // for (String artist : artistList) {
            // System.out.println(artist);
            // }

            song = new Song(data[1], data[2], data[3], data[4], Arrays.asList(data[5].split("#")));

            newSong = songRepository.save(song);
            // done for verfiying
            // System.out.println(newSong);

            // read next line
            readLine = br.readLine();
        }

        if (br != null)
            br.close();

        return "Songs Loaded successfully";



        // written for testing purpose
        // try {
        // br = new BufferedReader(new FileReader(csv));
        // while ((readLine = br.readLine()) != null) {
        // String[] data = readLine.split(",");

        // artistList = data[5].split("#");

        // // For Testing purpose
        // for(String artist: artistList) {
        // System.out.println( artist );
        // }

        // song = new Song( data[1], data[2], data[3], data[4],
        // Arrays.asList(artistList));

        // Song newSong = songRepository.save(song);
        // System.out.println(newSong);
        // }

        // } catch (IOException e) {
        // // TODO: handle exception
        // System.out.println("Error Message" + e.getMessage());
        // } finally {
        // if (br != null) {
        // try {
        // br.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // }
        // return "Songs Loaded successfully";

    }

}
