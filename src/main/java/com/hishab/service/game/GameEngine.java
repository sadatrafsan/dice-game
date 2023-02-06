package com.hishab.service.game;

import com.hishab.dto.response.DiceResponse;
import com.hishab.entity.Game;
import com.hishab.entity.Player;
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
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);
        boolean flag1 = false;
        boolean flag2 = false;
        int playerScore1 = 0;
        int playerScore2 = 0;

        while((playerScore1 < target) && (playerScore2 < target)){

            do{
                int score1 = roll();

                //Six, Start game
                if(isSix(score1)){

                    //Roll again
                    score1 = roll();

                    if(isSix(score1)){

                        if(!flag1){
                            playerScore1 = 0;
                            flag1 = true;
                        }
                        else{
                            playerScore1 += score1;
                        }
                    }
                    else {

                        if(isPenalty(score1) && flag1){

                            playerScore1 -= 4;
                        }

                    }

                }

                DataFactory.updateScore(game.getId(), player1.getName(), score1);
                log.info("Player name: {}" + " Total Score: {}" + " Current Value of Dice: {}", player1.getName(), playerScore1, score1);
                break;

            }while(playerScore1 < target);

            do{

                int score2 = roll();

                if(isSix(score2)){

                    score2 = roll();

                    if(isSix(score2)){

                        if(!flag2){
                            playerScore2 = 0;
                            flag2 = true;
                        }
                        else{
                            playerScore2 += score2;
                        }
                    }
                    else {

                        if(isPenalty(score2) && flag2){

                            playerScore2 -= 4;
                        }

                    }
                }

                DataFactory.updateScore(game.getId(), player2.getName(), score2);
                log.info("Player name: {}" + " Total Score: {}" + " Current Value of Dice: {}", player2.getName(), playerScore2, score2);
                break;

            }while(playerScore2 < target);
        }

        if(playerScore1 >= target){
            game.setWinnerId(player1.getId());
            DataFactory.updateGame(game);
            DataFactory.updateScore(game.getId(), player1.getName(), playerScore1);
            log.info("Winner: {}" + " Score: {}", player1.getName(), playerScore1);
        }

        if(playerScore2 >= target){
            game.setWinnerId(player2.getId());
            DataFactory.updateGame(game);
            DataFactory.updateScore(game.getId(), player2.getName(), playerScore2);
            log.info("Winner: {}" + " Score: {}", player2.getName(), playerScore2);
        }
    }

    public void simulateThreePlayerGame(Game game){

        int target = game.getHighestPoint();
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);
        Player player3 = game.getPlayers().get(2);
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        int playerScore1 = 0;
        int playerScore2 = 0;
        int playerScore3 = 0;

        while((playerScore1 < target) && (playerScore2 < target) && (playerScore3 < target)){

            //Player One
            do{
                int score1 = roll();

                //Six, Start game
                if(isSix(score1)){

                    //Roll again
                    score1 = roll();

                    if(isSix(score1)){

                        if(!flag1){
                            playerScore1 = 0;
                            flag1 = true;
                        }
                        else{
                            playerScore1 += score1;
                        }
                    }
                    else {

                        if(isPenalty(score1) && flag1){

                            playerScore1 -= 4;
                        }

                    }

                }

                DataFactory.updateScore(game.getId(), player1.getName(), score1);
                log.info("Player name: {}" + " Total Score: {}" + " Current Value of Dice: {}", player1.getName(), playerScore1, score1);
                break;

            }while(playerScore1 < target);

            //Player Two
            do{

                int score2 = roll();

                if(isSix(score2)){

                    score2 = roll();

                    if(isSix(score2)){

                        if(!flag2){
                            playerScore2 = 0;
                            flag2 = true;
                        }
                        else{
                            playerScore2 += score2;
                        }
                    }
                    else {

                        if(isPenalty(score2) && flag2){

                            playerScore2 -= 4;
                        }

                    }
                }

                DataFactory.updateScore(game.getId(), player2.getName(), score2);
                log.info("Player name: {}" + " Total Score: {}" + " Current Value of Dice: {}", player2.getName(), playerScore2, score2);
                break;

            }while(playerScore2 < target);

            //Player Three
            do{

                int score3 = roll();

                if(isSix(score3)){

                    score3 = roll();

                    if(isSix(score3)){

                        if(!flag2){
                            playerScore3 = 0;
                            flag2 = true;
                        }
                        else{
                            playerScore3 += score3;
                        }
                    }
                    else {

                        if(isPenalty(score3) && flag3){

                            playerScore2 -= 4;
                        }

                    }
                }

                DataFactory.updateScore(game.getId(), player3.getName(), score3);
                log.info("Player name: {}" + " Total Score: {}" + " Current Value of Dice: {}", player3.getName(), playerScore3, score3);
                break;

            }while(playerScore3 < target);
        }

        if(playerScore1 >= target){
            game.setWinnerId(player1.getId());
            DataFactory.updateGame(game);
            DataFactory.updateScore(game.getId(), player1.getName(), playerScore1);
            log.info("Winner: {}" + " Score: {}", player1.getName(), playerScore1);
        }

        if(playerScore2 >= target){
            game.setWinnerId(player2.getId());
            DataFactory.updateGame(game);
            DataFactory.updateScore(game.getId(), player2.getName(), playerScore2);
            log.info("Winner: {}" + " Score: {}", player2.getName(), playerScore2);
        }

        if(playerScore3 >= target){
            game.setWinnerId(player3.getId());
            DataFactory.updateGame(game);
            DataFactory.updateScore(game.getId(), player3.getName(), playerScore3);
            log.info("Winner: {}" + " Score: {}", player3.getName(), playerScore3);
        }
    }

}
