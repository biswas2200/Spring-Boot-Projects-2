package com.voters.eligiblevoters.service.impl;

import com.voters.eligiblevoters.dao.VoterAddressRepository;
import com.voters.eligiblevoters.dao.VoterRepository;
import com.voters.eligiblevoters.dto.VoterAddressDTO;
import com.voters.eligiblevoters.dto.VotersDTO;
import com.voters.eligiblevoters.model.VoterAddress;
import com.voters.eligiblevoters.model.Voters;
import com.voters.eligiblevoters.service.VotersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoterServiceImplTest {

    @InjectMocks
    private VoterServiceImpl voterService;
    @Mock
    private VoterRepository voterRepository;
    @Mock
    private VoterAddressRepository voterAddressRepository;

    private VoterAddressDTO mapAddressToDTO(VoterAddress voterAddress) {
        if (voterAddress == null) {
            return null;
        }

        VoterAddressDTO addressDTO = new VoterAddressDTO();
        addressDTO.setAddressId(voterAddress.getAddressId());
        addressDTO.setCityName(voterAddress.getCityName());
        addressDTO.setStateName(voterAddress.getStateName());
        addressDTO.setLongitudeAndLatitude(voterAddress.getLongitudeAndLatitude());

        return addressDTO;
    }

    private VotersDTO mapToDTO(Voters voters) {
        if (voters == null) {
            return null;
        }

        VotersDTO votersDTO = new VotersDTO();
        votersDTO.setVoterId(voters.getVoterId());
        votersDTO.setVoterAge(voters.getVoterAge());
        votersDTO.setName(voters.getName());

        VoterAddress voterAddress = voters.getVoterAddress();
        if (voterAddress != null) {
            VoterAddressDTO voterAddressDTO = mapAddressToDTO(voterAddress);
            votersDTO.setVoterAddressDTO(voterAddressDTO);
        }

        return votersDTO;
    }


    //post-method
    @Test
    public void saveVotersTest() {

        VotersDTO votersDTO = new VotersDTO();
        votersDTO.setVoterId(10L);
        votersDTO.setName("asdf");
        votersDTO.setVoterAge(29);

        VoterAddressDTO VoterAddressDTO = new VoterAddressDTO();
        VoterAddressDTO.setCityName("Mysore");
        VoterAddressDTO.setStateName("Karnataka");
        VoterAddressDTO.setLongitudeAndLatitude("394+DC");
        votersDTO.setVoterAddressDTO(VoterAddressDTO);


        //provide mock values
        Voters voters1 = new Voters();
        voters1.setVoterId(10L);
        voters1.setName("asdf");
        voters1.setVoterAge(29);
        //mock values
        when(voterRepository.save(Mockito.any())).thenReturn(voters1);
        VotersDTO voters = voterService.createVoters(votersDTO);
        Assertions.assertNotNull(voters);
        Assertions.assertEquals(voters.getVoterId(), 10L);
        Mockito.verify(voterRepository).save(Mockito.any());
    }


    //getAllVoters()
    @Test
    public void test2() {
        VoterRepository voterRepository = mock(VoterRepository.class);
        VoterAddressRepository voterAddressRepository = mock(VoterAddressRepository.class);
        Voters votersDTO = new Voters();
        votersDTO.setVoterId(12L);
        votersDTO.setVoterAge(23);
        votersDTO.setName("alk");

        when(voterRepository.findAllVoters()).thenReturn(List.of(votersDTO));

        VotersService voterService = new VoterServiceImpl(voterRepository, voterAddressRepository);

        List<VotersDTO> dtos = voterService.getAllVoters();

        Mockito.verify(voterRepository).findAllVoters();
        System.out.println("Mocked Repository Interaction: findAllVoters()");


        Assertions.assertNotNull(dtos);
        Assertions.assertEquals(1, dtos.size());
    }

    @Test
    public void test3() {
        when(voterRepository.findAllVoters())
                .thenReturn(new ArrayList<>());

        List<VotersDTO> dtos = voterService.getAllVoters();
        Assertions.assertEquals(0, dtos.size());
    }

    @Test
    public void getById_whenValidIdGiven_returnVoter() {
        Voters votersDTO = new Voters();
        votersDTO.setVoterId(12L);
        votersDTO.setVoterAge(23);
        votersDTO.setName("alk");
        when(voterRepository.findByIdVoter(Mockito.anyLong()))
                .thenReturn(votersDTO);
        VotersDTO voterById = voterService.getVoterById(12L);
        Assertions.assertNotNull(voterById);
        verify(voterRepository,times(1)).findByIdVoter(Mockito.anyLong());

    }

}