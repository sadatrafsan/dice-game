package com.hishab.entity;

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
