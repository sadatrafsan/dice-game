package com.hishab.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DiceResponse {

    private int score;
    private int status;
    private String message;
}
