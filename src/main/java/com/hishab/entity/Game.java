package com.hishab.entity;

import lombok.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Game {

    private long id;
    private String winnerId;
    private List<Player> players;
    private int highestPoint;
}
