package com.learn.junit5test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.junit5test.dto.UserDTO;
import com.learn.junit5test.entity.User;
import com.learn.junit5test.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @BeforeEach
    public void setUp(){
        mvc= MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }
    @Test
    void getAllUsers() throws Exception {
        mvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    void getUserById() throws Exception {
        mvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    void createUser() throws Exception {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Nah");
        when(userService.createUser(any())).thenReturn(new UserDTO());
        mvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateUser() throws Exception {
        UserDTO userDTO=new UserDTO();
        mvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isAccepted());
    }

    @Test
    void deleteUser() throws Exception {
        mvc.perform(delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}