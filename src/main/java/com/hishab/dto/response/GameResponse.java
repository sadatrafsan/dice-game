package com.hishab.dto.response;

import lombok.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class GameResponse {

    private Long id;
    private String winnerId;
    private Set<PlayerResponse> players;
    private int highestPoint;
}
