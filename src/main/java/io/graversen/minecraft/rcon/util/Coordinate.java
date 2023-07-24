package io.graversen.minecraft.rcon.util;

import io.graversen.minecraft.rcon.Defaults;

import java.util.Objects;

public class Coordinate {
    private final String theCoordinate;

    private Coordinate(String coordinate) {
        this.theCoordinate = Objects.requireNonNull(coordinate);
    }

    public String coordinate() {
        return theCoordinate;
    }

    public static Coordinate simple(long coordinate) {
        validate(coordinate);
        return new Coordinate(String.valueOf(coordinate));
    }

    public static Coordinate simple(double coordinate) {
        validate(coordinate);
        return new Coordinate(String.valueOf(coordinate));
    }

    public static Coordinate relative(long coordinate) {
        validate(coordinate);
        return new Coordinate("~" + coordinate);
    }

    public static Coordinate relative(double coordinate) {
        validate(coordinate);
        return new Coordinate("~" + coordinate);
    }

    private static void validate(double coordinate) {
        if (coordinate >= Defaults.WORLD_BOUND_MAX || coordinate <= Defaults.WORLD_BOUND_MIN) {
            throw new IllegalArgumentException("Coordinate is outside of world bounds");
        }
    }
}
