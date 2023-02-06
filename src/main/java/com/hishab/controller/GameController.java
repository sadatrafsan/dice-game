package com.hishab.controller;

import com.hishab.dto.request.GameRequest;
import com.hishab.dto.response.GameResponse;
import com.hishab.service.abstraction.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/game")
@RestController
public class GameController {

    private GameService gameService;

    @PostMapping
    public ResponseEntity startGame(@RequestBody GameRequest request){

        GameResponse response = gameService.startGame(request);
        gameService.simulateTheGame(response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/scores/{gameId}")
    public ResponseEntity getScores(@PathVariable("gameId") long gameId){

        return new ResponseEntity<>(gameService.getScoresByGameId(gameId), HttpStatus.OK);
    }
}
