package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserService Test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("create user should create USer")
    public void create_ShouldReturnUser() {
        // Arrange
        User expectedOutput = new User(1, "Om");
        when(userRepository.save(any(User.class))).thenReturn(expectedOutput);;

        // Act
        User actualOutput = userService.create("Om");

        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput);
        verify(userRepository, times(1)).save(any(User.class));
    }
}
