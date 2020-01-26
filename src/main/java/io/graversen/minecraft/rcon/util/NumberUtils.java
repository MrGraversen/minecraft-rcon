package io.graversen.minecraft.rcon.util;

public class NumberUtils {
    private NumberUtils() {

    }

    public static float enforceBound(float value, float lowerBound, float upperBound) {
        if (value > upperBound) {
            return upperBound;
        } else if (value < lowerBound) {
            return lowerBound;
        } else {
            return value;
        }
    }
}
