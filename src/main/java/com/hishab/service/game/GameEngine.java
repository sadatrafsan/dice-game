package com.hishab.service.game;

import com.hishab.dto.response.DiceResponse;
import com.hishab.entity.Game;
import com.hishab.entity.Player;
import com.hishab.entity.State;
import com.hishab.repository.DataFactory;
import com.hishab.service.abstraction.DiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
@AllArgsConstructor
public class GameEngine {

    private DiceService diceService;

    private boolean isSix(int score){

        return (score == 6);
    }

    private boolean isPenalty(int score){

        return (score == 4);
    }

    private int roll(){

        DiceResponse response = diceService.rollTheDice();

        if(response.getStatus() == 200){
            return response.getScore();
        }

        return new Random().nextInt(6) + 1;
    }

    private State dynamicGameSimulator(State state){

        do{
            int score = roll();

            if(isSix(score)){

                score = roll();

                if(isSix(score)){

                    if(!state.isFlag()){
                        state.setPlayerScore(0);
                        state.setFlag(true);
                    }
                    else{
                        int currentScore = state.getPlayerScore() + score;
                        state.setPlayerScore(currentScore);
                    }
                }
                else {

                    if(isPenalty(score) && state.isFlag()){

                        int currentScore = state.getPlayerScore() - 4 ;
                        state.setPlayerScore(currentScore);
                    }

                }

            }

            DataFactory.updateScore(state.getGame().getId(), state.getPlayer().getName(), state.getPlayerScore());
            log.info("Player name: {}" + " Total Score: {}" + " Current Value of Dice: {}", state.getPlayer().getName(), state.getPlayerScore(), score);
            break;

        }while(state.getPlayerScore() < state.getGame().getHighestPoint());

        return state;
    }

    public void simulateDynamicGame(Game game){

        int target = game.getHighestPoint();
        int numberOfPlayers = game.getPlayers().size();

        Player[] players;
        boolean[] flags;
        int[] playerScores;
        boolean menu;

        if(numberOfPlayers  == 2){
            players = new Player[2];
            players[0] = game.getPlayers().get(0);
            players[1] = game.getPlayers().get(1);
            flags = new boolean[2];
            playerScores = new int[2];
            menu = (playerScores[0] < target) && (playerScores[1] < target);
        }
        else if(numberOfPlayers == 3){
            players = new Player[3];
            players[0] = game.getPlayers().get(0);
            players[1] = game.getPlayers().get(1);
            players[2] = game.getPlayers().get(2);
            flags = new boolean[3];
            playerScores = new int[3];
            menu = (playerScores[0] < target) && (playerScores[1] < target) && (playerScores[2] < target);
        }
        else if(numberOfPlayers == 4){
            players = new Player[4];
            players[0] = game.getPlayers().get(0);
            players[1] = game.getPlayers().get(1);
            players[2] = game.getPlayers().get(2);
            players[3] = game.getPlayers().get(3);
            flags = new boolean[4];
            playerScores = new int[4];
            menu = (playerScores[0] < target) && (playerScores[1] < target) && (playerScores[2] < target) && (playerScores[3] < target);
        }
        else{
            throw new RuntimeException();
        }

        while(menu){

            State state1 = dynamicGameSimulator(new State(flags[0], playerScores[0], game, players[0]));
            flags[0] = state1.isFlag();
            playerScores[0] = state1.getPlayerScore();

            State state2 = dynamicGameSimulator(new State(flags[1], playerScores[1], game, players[1]));
            flags[1] = state2.isFlag();
            playerScores[1] = state2.getPlayerScore();


            if(numberOfPlayers == 2){
                menu = (playerScores[0] < target) && (playerScores[1] < target);
            }

            if(numberOfPlayers > 2){

                State state3 = dynamicGameSimulator(new State(flags[2], playerScores[2], game, players[2]));
                flags[2] = state3.isFlag();
                playerScores[2] = state3.getPlayerScore();

                menu = (playerScores[0] < target) && (playerScores[1] < target) && (playerScores[2] < target);

                if(numberOfPlayers > 3){

                    State state4 = dynamicGameSimulator(new State(flags[3], playerScores[3], game, players[3]));
                    flags[3] = state4.isFlag();
                    playerScores[3] = state4.getPlayerScore();

                    menu = (playerScores[0] < target) && (playerScores[1] < target) && (playerScores[2] < target) && (playerScores[3] < target);
                }
            }

        }

        for(int i=0; i<playerScores.length; i++){

            if(playerScores[i] >= target){
                game.setWinnerId(players[i].getId());
                DataFactory.updateGame(game);
                DataFactory.updateScore(game.getId(), players[i].getName(), playerScores[i]);
                log.info("Winner: {}" + " Score: {}", players[i].getName(), playerScores[i]);
                break;
            }
        }
    }

}
