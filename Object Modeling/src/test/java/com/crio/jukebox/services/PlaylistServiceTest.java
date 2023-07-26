package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("PlaylistService Test")
@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {
    
    @Mock
    private IUserRepository userRepository;

    @Mock 
    private ISongRepository songRepository;

    @Mock
    private IPlaylistRepository playlistRepository;

    @InjectMocks
    private PlaylistService playlistService;

    @Test
    @DisplayName("createPlaylist should create Playlist")
    public void createPlayList_shouldReturnPlaylist() {

        //Arrange 
        User user = new User( 1, "Om");
        Song song =new Song( 1 , "newSong" , "HipHop" , "Success" , "Legend" 
                 , Arrays.asList());
        Playlist expectedOutput  = new Playlist( 1 , 1 , "playListName" , Arrays.asList(song , song , song) );
        
        when(userRepository.findById(anyString())).thenReturn( Optional.of(user) );
        when(songRepository.findById(anyString())).thenReturn( Optional.of(song) );
        when( playlistRepository.save(any(Playlist.class))).thenReturn( expectedOutput ); 

        // Act 
        Playlist actualOutput = playlistService.createPlayList("1", "playlistName" , Arrays.asList("1", "2", "3"));
        
        // Assert 
        Assertions.assertEquals(expectedOutput, actualOutput);
        verify(userRepository, times(1)).findById(anyString());
        verify(songRepository, times(3)).findById(anyString());
        verify(playlistRepository, times(1)).save(any(Playlist.class));
    }

    // will be writting next test cases in free time 
}
