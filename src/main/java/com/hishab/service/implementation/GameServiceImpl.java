package com.hishab.service.implementation;

import com.hishab.dto.request.GameRequest;
import com.hishab.dto.response.GameResponse;
import com.hishab.dto.response.PlayerResponse;
import com.hishab.entity.Game;
import com.hishab.entity.Player;
import com.hishab.entity.ScoreResponse;
import com.hishab.repository.DataFactory;
import com.hishab.service.abstraction.GameService;
import com.hishab.service.game.GameEngine;
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

    private GameEngine gameEngine;

    @Override
    public List<ScoreResponse> getScoresByGameId(long gameId){

        List<ScoreResponse> responses = new ArrayList<>();

        if(DataFactory.isGameValid(gameId)){

            Map<String, Integer> scores = DataFactory.getScoresByGameId(gameId);

            for(String key : scores.keySet()){

                responses.add(
                        ScoreResponse.builder()
                                .playerName(key)
                                .score(scores.get(key))
                                .build());
            };
        }

        return responses;
    }


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

    @Async
    public void simulateTheGame(GameResponse response){

        Game game = DataFactory.getGameById(response.getId());

        int size = game.getPlayers().size();

        if(size == 2){

            gameEngine.simulateTwoPlayerGame(game);
        }
    }
}
