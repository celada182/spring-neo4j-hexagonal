package com.celada.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Person {
    private String dni;
    private String name;
    private String surname;
}
