package com.voters.eligiblevoters.service.impl;

import com.voters.eligiblevoters.dao.VoterAddressRepository;
import com.voters.eligiblevoters.dao.VoterRepository;
import com.voters.eligiblevoters.dto.VoterAddressDTO;
import com.voters.eligiblevoters.dto.VotersDTO;
import com.voters.eligiblevoters.model.VoterAddress;
import com.voters.eligiblevoters.model.Voters;
import com.voters.eligiblevoters.service.VotersService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoterServiceImpl implements VotersService {

    private VoterRepository voterRepository;

    private VoterAddressRepository voterAddressRepository;

    public VoterServiceImpl(VoterRepository voterRepository,VoterAddressRepository voterAddressRepository) {
        this.voterRepository = voterRepository;
        this.voterAddressRepository = voterAddressRepository;
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

    private Voters mapToEntity(VotersDTO votersDTO){
        if (votersDTO == null)
            return null;
        Voters entity = new Voters();
        entity.setVoterId(votersDTO.getVoterId());
        entity.setVoterAge(votersDTO.getVoterAge());
        entity.setName(votersDTO.getName());
        entity = voterRepository.save(entity);
        VoterAddress voterAddress = new VoterAddress();
        voterAddress.setCityName(votersDTO.getVoterAddressDTO().getCityName());
        voterAddress.setStateName(votersDTO.getVoterAddressDTO().getStateName());
        voterAddress.setLongitudeAndLatitude(votersDTO.getVoterAddressDTO().getLongitudeAndLatitude());
        voterAddress.setVoterId(entity.getVoterId());
        entity.setVoterAddress(voterAddress);
        voterAddressRepository.save(voterAddress);
        return entity;
    }
    private List<VotersDTO> convertToDoList(List<Voters> votersList){
        return votersList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    @Override
    public List<VotersDTO> getAllVoters() {
        List<Voters> votersList = voterRepository.findAllVoters();
        if(votersList.isEmpty()){
            return new ArrayList<>();
        }
        List<VotersDTO> filteredList = votersList.stream()
                .filter(voters -> voters.getVoterAge() > 18)
                .map(this::mapToDTO).collect(Collectors.toList());
        System.out.println(filteredList);
        return filteredList;
    }
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


    @Override
    public VotersDTO getVoterById(Long votersId) {
        Voters voters = voterRepository.findByIdVoter(votersId);
        if (voters == null)
            return null;
        return mapToDTO(voters);
    }

    @Override
    public VotersDTO createVoters(VotersDTO votersDTO) {
        mapToEntity(votersDTO);
        return votersDTO;
    }

    @Override
    public VotersDTO updateVoters(Long votersId, VotersDTO votersDTOs) {
        Voters existingVoters = voterRepository.findByIdVoter(votersId);
        if (existingVoters != null) {
            Voters voters = existingVoters;
            voters.setName(votersDTOs.getName());
            voters.setVoterAge(votersDTOs.getVoterAge());

            if (voters.getVoterAddress() != null && votersDTOs.getVoterAddressDTO() != null) {
                voters.getVoterAddress().setCityName(votersDTOs.getVoterAddressDTO().getCityName());
                voters.getVoterAddress().setStateName(votersDTOs.getVoterAddressDTO().getStateName());
                voters.getVoterAddress().setLongitudeAndLatitude(votersDTOs.getVoterAddressDTO().getLongitudeAndLatitude());

            }
            voterRepository.save(voters);
            return mapToDTO(voters);
        }
        else {
            return null;
        }
    }

    @Override
    public void deleteVoters(Long votersId) {
    voterRepository.deleteById(votersId);
    }

}
