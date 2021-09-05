package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base;

import com.fasterxml.jackson.annotation.JsonValue;


public enum Popularity {


    ZERO("ZERO"), 
    LOW("LOW"), 
    MEDIUM("MEDIUM"), 
    HIGH("HIGH"),
    HIGHEST("HIGHEST");

    private String value;

    Popularity(String value) {
        this.value = value;
    }

    @JsonValue
    public static Popularity fromValue(String value) {
        for (Popularity state : values()) {
            if (state.value.equalsIgnoreCase(value)) {
                return state;
            }
        }
        return null;
    }
}
