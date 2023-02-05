package com.hishab.service.implementation;

import com.hishab.dto.request.PlayerRequest;
import com.hishab.dto.response.PlayerResponse;
import com.hishab.entity.Player;
import com.hishab.repository.DataFactory;
import com.hishab.service.abstraction.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    public void savePlayer(PlayerRequest request){

        log.debug(request.toString());

        Player player = Player.builder()
                .id(UUID.randomUUID().toString().substring(0,4))
                .name(request.getName())
                .age(request.getAge())
                .build();

        DataFactory.addPlayer(player);
    }

    @Override
    public List<PlayerResponse> getPlayers(){

        List<PlayerResponse> players = new ArrayList<>();

        DataFactory.getPlayers().forEach(player -> {
            players.add(
                    PlayerResponse.builder()
                            .id(player.getId())
                            .name(player.getName())
                            .age(player.getAge())
                            .build()
            );
        });

        return players;
    }
}
