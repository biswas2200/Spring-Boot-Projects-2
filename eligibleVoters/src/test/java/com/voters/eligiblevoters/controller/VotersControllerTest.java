package com.voters.eligiblevoters.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voters.eligiblevoters.dto.VotersDTO;
import com.voters.eligiblevoters.service.VotersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VotersController.class)
class VotersControllerTest {

    @MockBean
    VotersService votersService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createVoters() throws Exception {
        VotersDTO votersDTO = new VotersDTO();
        votersDTO.setVoterAge(13);
        votersDTO.setName("bc");
        votersDTO.setVoterId(1L);
        when(votersService.createVoters(any())).thenReturn(votersDTO);
        mockMvc.perform(post("/voters/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new VotersDTO())))
                .andExpect(status().isCreated());

    }

    @Test
    void getVoterById() throws Exception {
        VotersDTO votersDTO = new VotersDTO();
        votersDTO.setVoterId(1L);
        votersDTO.setVoterAge(20);
        votersDTO.setName("asd");
        when(votersService.getVoterById(any())).thenReturn(votersDTO);
        mockMvc.perform(get("/voters/{votersId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


        @Test
    void getAllVoters() throws Exception {
        List<VotersDTO> votersDTOList = new ArrayList<>();
        VotersDTO votersDTO = new VotersDTO();
        votersDTO.setVoterId(12L);
        votersDTO.setVoterAge(23);
        votersDTO.setName("alk");
        when(votersService.getAllVoters()).thenReturn(votersDTOList);
        mockMvc.perform(get("/voters/")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void deleteVoters() throws Exception {
        doNothing().when(votersService).deleteVoters(Mockito.any());

        mockMvc.perform(delete("/voters/{votersId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(204));
    }

}