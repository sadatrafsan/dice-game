package com.hishab.controller;

import com.hishab.dto.request.GameRequest;
import com.hishab.dto.response.GameResponse;
import com.hishab.repository.DataFactory;
import com.hishab.service.abstraction.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/game")
@RestController
public class GameController {

    private GameService gameService;


    @GetMapping("/scores/{gameId}")
    public ResponseEntity getScores(@PathVariable("gameId") long gameId){

        if(DataFactory.isGameValid(gameId)){
            return new ResponseEntity<>(gameService.getScoresByGameId(gameId), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not Found!", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity startGame(@RequestBody GameRequest request){

        if(request.getPlayers().size() > 4 || request.getPlayers().size() < 2){

            return new ResponseEntity<>("Number of participant must be between 2 to 4!", HttpStatus.NOT_ACCEPTABLE);
        }
        else{

            GameResponse response = gameService.startGame(request);
            gameService.simulateTheGame(response);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }
}
