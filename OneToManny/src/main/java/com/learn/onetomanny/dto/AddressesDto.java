package com.learn.onetomanny.dto;
import lombok.Data;

@Data
public class AddressesDto {
    private Long addressId;
    private String city;
    private String state;
    private String country;

}
