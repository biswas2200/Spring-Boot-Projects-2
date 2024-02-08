package com.learn.junit5test.service.impl;

import com.learn.junit5test.dto.UserDTO;
import com.learn.junit5test.entity.User;
import com.learn.junit5test.exception.UserNotFoundException;
import com.learn.junit5test.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void getAllUserTest(){
        List<User> userList =new ArrayList<>();
        userList.add(new User(1L,"Ham",22));
        userList.add(new User(2L,"Bam",21));
        when(userRepository.findAll()).thenReturn(userList);

        List<UserDTO> result=userService.getAllUsers();

        assertNotNull(userList);
        assertEquals(2,result.size());
        assertEquals("Ham",result.get(0).getName());
        assertEquals("Bam",result.get(1).getName());
        assertEquals(2L,result.get(1).getId());
        assertEquals(1L,result.get(0).getId());

        verify(userRepository,times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User(userId, "John Doe",18);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(userId);

        assertEquals(userId, result.getId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void GetUserById_UserNotFoundTest(){
        Long userId=1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,()->userService.getUserById(userId));
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");

        User savedUser = new User(1L, "John Doe",20);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserDTO result = userService.createUser(userDTO);

        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Smith");

        User existingUser = new User(userId, "John Doe",23);
        User updatedUser = new User(userId, "John Smith",24);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        UserDTO result = userService.updateUser(userId, userDTO);

        assertEquals(userId, result.getId());
        assertEquals("John Smith", result.getName());
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Smith");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, userDTO));
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
    @Test
    public void testVotableUser(){
            List<User> userList = new ArrayList<>();
            User user1 = new User();
            user1.setId(1L);
            user1.setName("John");
            user1.setAge(20);
            userList.add(user1);

            User user2 = new User();
            user2.setId(2L);
            user2.setName("Jane");
            user2.setAge(17);
            userList.add(user2);

            when(userRepository.findAll()).thenReturn(userList);

            List<UserDTO> votableUsers = userService.votableUser(null);

            assertThat(votableUsers.get(0).getAge()).isGreaterThan(18);
            assertThat(votableUsers.get(0).getName()).isAlphabetic();

        }
}
