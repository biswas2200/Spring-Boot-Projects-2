package com.voters.eligiblevoters.dao;

import com.voters.eligiblevoters.model.VoterAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterAddressRepository extends JpaRepository<VoterAddress, Long> {

}
