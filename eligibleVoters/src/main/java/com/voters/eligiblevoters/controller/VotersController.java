package com.voters.eligiblevoters.controller;

import com.voters.eligiblevoters.dto.VotersDTO;
import com.voters.eligiblevoters.service.VotersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voters")
public class VotersController {
    private final VotersService votersService;

    @Autowired
    public VotersController(VotersService votersService) {
        this.votersService = votersService;
    }

    @GetMapping("/")
    public ResponseEntity<List<VotersDTO>> getAllVoters() {
        List<VotersDTO> voters = votersService.getAllVoters();
        return new ResponseEntity<>(voters, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<VotersDTO> createVoters(@RequestBody VotersDTO votersDTO){
        return new ResponseEntity<>(votersService.createVoters(votersDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{votersId}")
    public ResponseEntity<VotersDTO> updateVoters(@PathVariable Long votersId, @RequestBody VotersDTO votersDTO){
        try {
            VotersDTO updatedVoters = votersService.updateVoters(votersId,votersDTO );
            if (updatedVoters != null)
                return new ResponseEntity<>(updatedVoters, HttpStatus.FOUND);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{votersId}")
    public ResponseEntity<VotersDTO> getVoterById(@PathVariable Long votersId) {
        VotersDTO voter = votersService.getVoterById(votersId);

        if (voter != null) {
            return new ResponseEntity<>(voter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{votersId}")
    public ResponseEntity<Void> deleteVoters(@PathVariable Long votersId) {
        votersService.deleteVoters(votersId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
