package com.hishab.service.abstraction;

import com.hishab.dto.request.PlayerRequest;
import com.hishab.dto.response.PlayerResponse;

import java.util.List;

public interface PlayerService {

    void savePlayer(PlayerRequest request);
    List<PlayerResponse> getPlayers();
}
