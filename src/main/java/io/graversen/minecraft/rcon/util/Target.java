package io.graversen.minecraft.rcon.util;

import java.util.Objects;

public class Target {
    private final String targetString;

    private Target(String targetString) {
        this.targetString = Objects.requireNonNull(targetString);
    }

    public static Target player(String playerName) {
        return new Target(playerName);
    }

    public static Target selector(Selector selector) {
        return new Target(selector.getSelectorString());
    }

    public String getTargetString() {
        return targetString;
    }

    @Override
    public String toString() {
        return targetString;
    }
}
