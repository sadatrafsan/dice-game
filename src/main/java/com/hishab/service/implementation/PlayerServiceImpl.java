package com.hishab.service.implementation;

import com.hishab.dto.request.PlayerRequest;
import com.hishab.entity.Player;
import com.hishab.repository.DataFactory;
import com.hishab.service.abstraction.PlayerService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class PlayerServiceImpl implements PlayerService {

    public void savePlayer(PlayerRequest request){

        Player player = Player.builder()
                .id(UUID.randomUUID().toString().substring(0,3))
                .name(request.getName())
                .age(request.getAge())
                .build();

        DataFactory.addPlayer(player);
    }


}
