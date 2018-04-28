package io.graversen.minecraft.rcon.util;

public enum Difficulties
{
    PEACEFUL,
    EASY,
    NORMAL,
    HARD;

    public String getDifficultyName()
    {
        return name().toLowerCase();
    }
}
