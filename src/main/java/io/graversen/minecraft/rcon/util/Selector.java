package io.graversen.minecraft.rcon.util;

public enum Selector {
    ALL_PLAYERS("@a"),
    RANDOM_PLAYER("@r"),
    NEAREST_PLAYER("@p"),
    ALL_ENTITIES_AND_PLAYERS("@e");

    private final String selectorString;

    Selector(String selectorString) {
        this.selectorString = selectorString;
    }

    public String getSelectorString() {
        return selectorString;
    }
}
