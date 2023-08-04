package io.graversen.minecraft.rcon.util;

public class NumberUtils {
    private NumberUtils() {}

    public static float enforceBound(float value, float lowerBound, float upperBound) {
        if (value > upperBound) {
            return upperBound;
        } else return Math.max(value, lowerBound);
    }
}
