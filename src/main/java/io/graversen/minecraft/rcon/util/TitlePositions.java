package io.graversen.minecraft.rcon.util;

public enum TitlePositions
{
    TITLE,
    SUBTITLE,
    ACTIONBAR;

    public String getTitlePositionValue()
    {
        return name().toLowerCase();
    }
}
