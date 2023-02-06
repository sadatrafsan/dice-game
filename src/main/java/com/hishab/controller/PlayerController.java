package com.hishab.controller;

import com.hishab.dto.request.PlayerRequest;
import com.hishab.service.abstraction.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/players")
@RestController
public class PlayerController {

    private PlayerService playerService;

    @GetMapping
    public ResponseEntity getPlayers(){

        return new ResponseEntity<>(playerService.getPlayers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createPlayer(@RequestBody PlayerRequest request){

        playerService.savePlayer(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
