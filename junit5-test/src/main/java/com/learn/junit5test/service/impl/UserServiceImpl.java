package com.learn.junit5test.service.impl;

import com.learn.junit5test.dto.UserDTO;
import com.learn.junit5test.entity.User;
import com.learn.junit5test.exception.UserNotFoundException;
import com.learn.junit5test.repository.UserRepository;
import com.learn.junit5test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return convertToDTO(user);
    }
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setName(userDTO.getName());
        user.setAge(user.getAge());
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public List<UserDTO> votableUser(UserDTO userDTO){
        List<User> user=userRepository.findAll();

       return user.stream()
                .filter(user1 ->user1.getAge()>=18)
                .map(this::convertToDTO)
                .toList();
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAge(user.getAge());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        return user;
    }
}
