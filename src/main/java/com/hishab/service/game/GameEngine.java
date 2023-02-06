package com.hishab.service.game;

import com.hishab.dto.response.DiceResponse;
import com.hishab.entity.Game;
import com.hishab.repository.DataFactory;
import com.hishab.service.abstraction.DiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    private boolean isZero(int score){

        return (score == 0);
    }

    private int roll(){

        DiceResponse response = diceService.rollTheDice();

        if(response.getStatus() == 200){
            return response.getScore();
        }

        return 0;
    }

    public void simulateTwoPlayerGame(Game game){

        int target = game.getHighestPoint();
        boolean flag1 = false;
        boolean flag2 = false;
        int player1 = 0;
        int player2 = 0;

        while((player1 < target) && (player2 < target)){

            do{
                int score1 = roll();

                //Six, Start game
                if(isSix(score1)){

                    //Roll again
                    score1 = roll();

                    if(isSix(score1)){

                        if(!flag1){
                            player1 = 0;
                            flag1 = true;
                        }
                        else{
                            player1 += score1;
                        }
                    }
                    else {

                        if(isPenalty(score1) && flag1){

                            player1 -= 4;
                        }

                    }

                }

                log.info("Player name: {}" + " Total Score: {}" + " Current Value of Dice: {}", game.getPlayers().get(0).getName(), player1, score1);
                break;

            }while(player1 < target);

            do{

                int score2 = roll();

                if(isSix(score2)){

                    score2 = roll();

                    if(isSix(score2)){

                        if(!flag2){
                            player2 = 0;
                            flag2 = true;
                        }
                        else{
                            player2 += score2;
                        }
                    }
                    else {

                        if(isPenalty(score2) && flag2){

                            player2 -= 4;
                        }

                    }
                }

                log.info("Player name: {}" + " Total Score: {}" + " Current Value of Dice: {}", game.getPlayers().get(1).getName(), player2, score2);
                break;

            }while(player2 < target);
        }

        if(player1 >= target){
            game.setWinnerId(game.getPlayers().get(0).getId());
            DataFactory.updateGame(game);
            log.info("Winner: {}" + " Score: {}", game.getPlayers().get(0).getName(), player1);
        }

        if(player2 >= target){
            game.setWinnerId(game.getPlayers().get(1).getId());
            DataFactory.updateGame(game);
            log.info("Winner: {}" + " Score: {}", game.getPlayers().get(1).getName(), player2);
        }
    }

}
