package io.graversen.minecraft.rcon.util;

public enum Weathers
{
    CLEAR,
    RAIN,
    THUNDER;

    public String getWeatherString()
    {
        return name().toLowerCase();
    }
}
