package com.voters.eligiblevoters.dao;

import com.voters.eligiblevoters.model.Voters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterRepository extends JpaRepository<Voters, Long> {
   @Query("select v from Voters v")
    List<Voters> findAllVoters();

   @Query("select v from Voters v where v.voterId =: voterId")
    Voters findByIdVoter(Long votersId);
}
