package io.graversen.minecraft.rcon.util;

public enum TimeLabels {
    DAY(1000),
    NIGHT(13000),
    NOON(6000),
    MIDNIGHT(18000);

    private final int timeValue;

    TimeLabels(int timeValue) {
        this.timeValue = timeValue;
    }

    public int timeValue() {
        return timeValue;
    }
}
