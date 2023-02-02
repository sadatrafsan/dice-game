package com.hishab.service.implementation;

import com.hishab.dto.response.DiceResponse;
import com.hishab.entity.Dice;
import com.hishab.service.abstraction.DiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DiceServiceImpl implements DiceService {

    @Value("${service.url}")
    private String serviceUrl;

    private final RestTemplate restTemplate;

    @Override
    public DiceResponse rollTheDice(){

        ResponseEntity<Dice> response = restTemplate.getForEntity(serviceUrl, Dice.class);

        if(response.getStatusCode().isError()){

            return DiceResponse.builder()
                    .score(-1)
                    .message(response.getStatusCode().getReasonPhrase())
                    .status(response.getStatusCodeValue())
                    .build();
        }

        return DiceResponse.builder()
                .score(response.getBody().getScore())
                .message(response.getStatusCode().getReasonPhrase())
                .status(response.getStatusCodeValue())
                .build();
    }
}
