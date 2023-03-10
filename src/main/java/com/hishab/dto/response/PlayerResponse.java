package com.hishab.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PlayerResponse {

    private String id;
    private String name;
    private int age;
}
