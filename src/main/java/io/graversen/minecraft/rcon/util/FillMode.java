package io.graversen.minecraft.rcon.util;

public enum FillMode {
    DESTROY,
    HOLLOW,
    KEEP,
    OUTLINE,
    REPLACE;

    public String getFillModesString() {
        return name().toLowerCase();
    }
}
