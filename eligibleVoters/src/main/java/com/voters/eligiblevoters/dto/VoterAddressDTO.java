package com.voters.eligiblevoters.dto;

import lombok.Data;

@Data
public class VoterAddressDTO {
    private Long addressId;
    private String cityName;
    private String stateName;
    private String longitudeAndLatitude;
}
