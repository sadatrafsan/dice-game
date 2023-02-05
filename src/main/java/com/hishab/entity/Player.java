package com.hishab.entity;

import lombok.*;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Player {

    private String id;
    private String name;
    private int age;
    private Map<Long, Integer> scorecard = new HashMap<>();
}
