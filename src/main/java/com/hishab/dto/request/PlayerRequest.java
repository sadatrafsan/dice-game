package com.hishab.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PlayerRequest {
    private String name;
    private int age;
}
