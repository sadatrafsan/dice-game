package com.hishab.repository;

import com.hishab.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class DataFactory {

    private static List<Player> players = new ArrayList<>();

    public static void addPlayer(Player player){

        players.add(player);
    }

    public static List<Player> getPlayers(){

        return players;
    }

}
