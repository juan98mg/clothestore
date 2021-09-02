package com.experimentaly.api.clothesstore.infrastructure.configuration;

public enum Popularity {

    ZERO("ZERO"), LOW("LOW"), MEDIUM("MEDIUM"), HIGH("HIGH"), HIGHEST("HIGHEST");

    private String value;

    Popularity(String value) {
        this.value = value;
    }

    public static Popularity fromValue(String value) {
        for (Popularity state : values()) {
            if (state.value.equalsIgnoreCase(value)) {
                return state;
            }
        }
        return null;
    }
}
