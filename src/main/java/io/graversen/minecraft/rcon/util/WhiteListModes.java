package io.graversen.minecraft.rcon.util;

public enum WhiteListModes {
    ADD,
    LIST,
    OFF,
    ON,
    RELOAD,
    REMOVE;

    public String getModeName() {
        return name().toLowerCase();
    }
}
