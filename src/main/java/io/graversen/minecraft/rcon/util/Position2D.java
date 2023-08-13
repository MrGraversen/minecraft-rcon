package io.graversen.minecraft.rcon.util;

public record Position2D(Coordinate x, Coordinate z) {

    public static Position2D simple(long x, long z) {
        return new Position2D(
                Coordinate.simple(x),
                Coordinate.simple(z)
        );
    }

    public static Position2D relative() {
        return new Position2D(
                Coordinate.relative(0),
                Coordinate.relative(0)
        );
    }

    @Override
    public String toString() {
        return x().coordinate() + " " + z().coordinate();
    }
}
