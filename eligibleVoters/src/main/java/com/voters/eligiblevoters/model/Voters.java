package com.voters.eligiblevoters.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voters_info")
public class Voters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voter_id")
    private Long voterId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer voterAge;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @OneToOne(mappedBy = "voters", cascade = CascadeType.ALL)
    private VoterAddress voterAddress;
}
