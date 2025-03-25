package com.celada.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class Person {
    private UUID id;
    private String name;
    private String surname;
}
