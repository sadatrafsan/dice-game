package com.hishab.entity;

import lombok.*;

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
}
