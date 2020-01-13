package io.graversen.minecraft.rcon.util;

public enum TimeLabels {
    DAY,
    NIGHT,
    NOON,
    MIDNIGHT;

    public String getTimeString() {
        return name().toLowerCase();
    }
}
