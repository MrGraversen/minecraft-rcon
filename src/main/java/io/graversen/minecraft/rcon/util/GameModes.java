package io.graversen.minecraft.rcon.util;

public enum GameModes
{
    SURVIVAL,
    CREATIVE,
    ADVENTURE,
    SPECTATOR;

    public String getGameModeString()
    {
        return name().toLowerCase();
    }
}
