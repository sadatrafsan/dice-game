package com.hishab.repository;

import com.hishab.entity.Game;
import com.hishab.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFactory {

    private static List<Player> players = new ArrayList<>();
    private static Map<String, Player> playerMap = new HashMap<>();
    private static Map<Long, Game> gameMap = new HashMap<>();

    public static void addPlayer(Player player){

        players.add(player);
        playerMap.put(player.getId(), player);
    }

    public static List<Player> getPlayers(){

        return players;
    }

    public static List<Player> getParticipants(List<String> ids){

        List<Player> participants = new ArrayList<>();

        ids.forEach(id -> {
            participants.add(playerMap.get(id));
        });

        return participants;
    }

    public static void createGame(Game game){

        gameMap.put(game.getId(), game);
    }

    public static Game getGameById(long id){

        return gameMap.get(id);
    }

    public static void updateGame(Game game){

        gameMap.put(game.getId(), game);
    }
}
