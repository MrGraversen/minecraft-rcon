package io.graversen.minecraft.rcon.util;

public class ColorUtils {
    private ColorUtils() {}

    public static class Qualities {

        private Qualities() {}

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
