package io.graversen.minecraft.rcon.util;

public enum Difficulty {
    PEACEFUL,
    EASY,
    NORMAL,
    HARD;

    public String getDifficultyName() {
        return name().toLowerCase();
    }
}
