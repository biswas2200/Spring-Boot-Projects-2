package com.learn.junit5test.service;

import com.learn.junit5test.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    List<UserDTO> votableUser(UserDTO userDTO);
}
