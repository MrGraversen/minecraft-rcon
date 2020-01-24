package io.graversen.minecraft.rcon.util;

import io.graversen.minecraft.rcon.Defaults;

public enum Dimensions {
    OVERWORLD,
    THE_NETHER,
    THE_END;

    public String getNamespacedDimensionString() {
        return String.format("%s:%s", Defaults.MINECRAFT, name()).toLowerCase();
    }
}
