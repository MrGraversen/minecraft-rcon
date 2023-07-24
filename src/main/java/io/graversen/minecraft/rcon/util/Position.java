package io.graversen.minecraft.rcon.util;

public record Position(Coordinate x, Coordinate y, Coordinate z) {

    public static Position simple(long x, long y, long z) {
        return new Position(
                Coordinate.simple(x),
                Coordinate.simple(y),
                Coordinate.simple(z)
        );
    }

    public static Position relative() {
        return new Position(
                Coordinate.relative(0),
                Coordinate.relative(0),
                Coordinate.relative(0)
        );
    }

    @Override
    public String toString() {
        return x().coordinate() + " " + y().coordinate() + " " + z().coordinate();
    }
}
