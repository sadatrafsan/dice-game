package com.hishab.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ScoreResponse {

    private String playerName;
    private int score;
}
