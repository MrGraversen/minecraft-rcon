package io.graversen.minecraft.rcon.util;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public class Position {
    private final long x;
    private final long y;
    private final long z;

    public Position(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    @Override
    public String toString() {
        return StringSubstitutor.replace(
                "${x} ${y} ${z}",
                Map.of(
                        "x", x,
                        "y", y,
                        "z", z
                )
        );
    }
}
