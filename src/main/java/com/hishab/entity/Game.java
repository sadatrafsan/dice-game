package com.hishab.entity;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Game {

    private String id;
    private String winnerId;
    private List<Player> players;
    private int highestPoint;
}
