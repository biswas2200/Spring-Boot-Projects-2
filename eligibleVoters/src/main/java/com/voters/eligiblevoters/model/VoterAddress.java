package com.voters.eligiblevoters.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "voters_address_info")
public class VoterAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "lonANDlat")
    private String longitudeAndLatitude;

    @Column(name = "voter_id")
    private Long voterId;

    @OneToOne
    @JoinColumn(name = "voter_id" , referencedColumnName = "voter_id",insertable = false,updatable = false)
    private Voters voters;
}
