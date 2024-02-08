package com.voters.eligiblevoters.dto;

import com.voters.eligiblevoters.model.VoterAddress;
import lombok.Data;

@Data
public class VotersDTO {
    private Long voterId;
    private String name;
    private Integer voterAge;
    private VoterAddressDTO voterAddressDTO;

}
