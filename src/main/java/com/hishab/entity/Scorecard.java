package com.hishab.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Scorecard {

    private int gameId;
    private String playerId;
    private int score;
}
