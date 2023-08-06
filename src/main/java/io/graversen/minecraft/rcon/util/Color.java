package io.graversen.minecraft.rcon.util;

public enum Color {
    BLACK,
    DARK_BLUE,
    DARK_GREEN,
    DARK_AQUA,
    DARK_RED,
    DARK_PURPLE,
    GOLD,
    GRAY,
    DARK_GRAY,
    BLUE,
    GREEN,
    AQUA,
    RED,
    PINK,
    PURPLE,
    LIGHT_PURPLE,
    YELLOW,
    WHITE;

    public String getColorName() {
        return name().toLowerCase();
    }
}
