package io.graversen.minecraft.rcon.util;

public enum GameMode {
    SURVIVAL,
    CREATIVE,
    ADVENTURE,
    SPECTATOR;

    public String getGameModeString() {
        return name().toLowerCase();
    }
}
