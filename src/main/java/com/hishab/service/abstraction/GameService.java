package com.hishab.service.abstraction;

import com.hishab.dto.request.GameRequest;
import com.hishab.dto.response.GameResponse;
import com.hishab.dto.response.ScoreResponse;
import java.util.List;

public interface GameService {

    List<ScoreResponse> getScoresByGameId(long gameId);
    GameResponse startGame(GameRequest request);
    void simulateTheGame(GameResponse response);
}
