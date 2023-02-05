package com.hishab.dto.request;

import lombok.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class GameRequest {

    private List<String> players;
    private int highestPoint;
}
