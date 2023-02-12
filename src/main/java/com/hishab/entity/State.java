package com.hishab.entity;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class State {

    private boolean flag;
    private int playerScore;
    private Game game;
    private Player player;
}
