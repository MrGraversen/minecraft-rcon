package io.graversen.minecraft.rcon.util;

public enum TitlePosition {
    TITLE,
    SUBTITLE,
    ACTIONBAR;

    public String getTitlePositionValue() {
        return name().toLowerCase();
    }
}
