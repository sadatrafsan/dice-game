package com.hishab.controller;

import com.hishab.dto.request.PlayerRequest;
import com.hishab.service.abstraction.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/players")
@RestController
public class PlayerController {

    private PlayerService playerService;

    @GetMapping
    public ResponseEntity getPlayers(){

        return new ResponseEntity<>(playerService.getPlayers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createPlayer(PlayerRequest request){

        playerService.savePlayer(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
