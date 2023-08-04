package io.graversen.minecraft.rcon.util;

public enum Weather {
    CLEAR,
    RAIN,
    THUNDER;

    public String getWeatherString() {
        return name().toLowerCase();
    }
}
