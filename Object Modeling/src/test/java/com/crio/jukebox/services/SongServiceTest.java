package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.Arrays;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("SongServiceTest")
@ExtendWith(MockitoExtension.class)
public class SongServiceTest {

    @Mock 
    private ISongRepository songRepository;

    @InjectMocks
    private SongService songService;

    @Test
    @DisplayName("loadSongsFromCsvToSongRepositories method should return Songs Loaded successfully")
    public void loadSongsFromCsvToSongRepositories_ShouldAddSuccessForAllString() throws IOException {      
        // Arrange 
        Song song=new Song( 1 , "Messi_Messi_Messi" , "Hip_Hop" , "Messi" , 
           "Messii", Arrays.asList("Messii","Anuradha","Trirupati","Megna","Ayush"));
        String expectedOutput = "Songs Loaded successfully";
        
        when(songRepository.save(any(Song.class))).thenReturn(song);

        //Act
        String actualOutput = songService.loadSongsFromCsvToSongRepositories("songs.csv");

        //Assert 
        Assertions.assertEquals(expectedOutput , actualOutput);
        verify(songRepository, times(30)).save(any(Song.class));
    }
    
}
