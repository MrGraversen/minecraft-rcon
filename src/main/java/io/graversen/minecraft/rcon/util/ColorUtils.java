package io.graversen.minecraft.rcon.util;

public class ColorUtils {
    private ColorUtils() {}

    public static class Qualities {

        private Qualities() {}

        public static Color common() {
            return Color.GREEN;
        }

        public static Color uncommon() {
            return Color.BLUE;
        }

        public static Color epic() {
            return Color.DARK_PURPLE;
        }

        public static Color legendary() {
            return Color.YELLOW;
        }

        public static Color mythic() {
            return Color.RED;
        }
    }
}
