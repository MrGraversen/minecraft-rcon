package io.graversen.minecraft.rcon.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ColorUtils {
    @UtilityClass
    public static class Qualities {
        public static Colors common() {
            return Colors.GREEN;
        }

        public static Colors uncommon() {
            return Colors.BLUE;
        }

        public static Colors epic() {
            return Colors.DARK_PURPLE;
        }

        public static Colors legendary() {
            return Colors.YELLOW;
        }

        public static Colors mythic() {
            return Colors.RED;
        }
    }
}
