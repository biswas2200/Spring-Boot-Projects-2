package com.voters.eligiblevoters.service;

import com.voters.eligiblevoters.dto.VotersDTO;

import java.util.List;

public interface VotersService {
    public List<VotersDTO> getAllVoters();
    public VotersDTO getVoterById(Long votersId);
    public VotersDTO createVoters(VotersDTO votersDTO);
    public VotersDTO updateVoters(Long votersId, VotersDTO votersDTOs);
    public void deleteVoters(Long votersId);
}
