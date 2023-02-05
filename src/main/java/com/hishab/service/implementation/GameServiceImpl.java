package com.hishab.service.implementation;

import com.hishab.dto.request.GameRequest;
import com.hishab.dto.response.DiceResponse;
import com.hishab.dto.response.GameResponse;
import com.hishab.dto.response.PlayerResponse;
import com.hishab.entity.Game;
import com.hishab.entity.Player;
import com.hishab.repository.DataFactory;
import com.hishab.service.abstraction.DiceService;
import com.hishab.service.abstraction.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private DiceService diceService;

    public GameResponse startGame(GameRequest request){

        List<Player> players = DataFactory.getParticipants(request.getPlayers());

        Game game = Game.builder()
                .id(System.currentTimeMillis())
                .players(players)
                .highestPoint(request.getHighestPoint())
                .build();

        DataFactory.createGame(game);

        return GameResponse.builder()
                .id(game.getId())
                .players(new HashSet<>(toPlayer(players)))
                .highestPoint(game.getHighestPoint())
                .build();
    }

    private List<PlayerResponse> toPlayer(List<Player> players){

        List<PlayerResponse> responses = new ArrayList<>();

        players.forEach(player -> {
            responses.add(PlayerResponse.builder()
                    .id(player.getId())
                    .name(player.getName())
                    .age(player.getAge())
                    .build());
        });

        return responses;
    }

    private int rollTheDice(){

        DiceResponse diceResponse = diceService.rollTheDice();

        if(diceResponse.getStatus() == 200){
            return diceResponse.getScore();
        }

        return 0;
    }


    @Async
    public void simulateTheGame(GameResponse response){

        Game game = DataFactory.getGameById(response.getId());

        int size = game.getPlayers().size();

        if(size == 2){

            int array[] = {0,0};

            Player player1 = game.getPlayers().get(0);
            player1.setScorecard(Map.of(game.getId(), 0));
            int player1Score = player1.getScorecard().get(game.getId());

            Player player2 = game.getPlayers().get(1);
            player2.setScorecard(Map.of(game.getId(), 0));
            int player2Score = player1.getScorecard().get(game.getId());

            int highestPoint = game.getHighestPoint();

            while((player1Score < highestPoint) && (player2Score < highestPoint)){

                int score = rollTheDice();

                if(isBetween1to5(score)){
                    score = rollTheDice();
                }


            }

        }


    }

    private boolean isBetween1to5(int score){

        return score > 0 && score < 6;
    }

    private boolean is6(int score){

        return score == 6;
    }
}
