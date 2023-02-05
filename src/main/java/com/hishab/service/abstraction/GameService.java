package com.hishab.service.abstraction;

import com.hishab.dto.request.GameRequest;
import com.hishab.dto.response.GameResponse;

public interface GameService {

    GameResponse startGame(GameRequest request);
    void simulateTheGame(GameResponse response);
}
