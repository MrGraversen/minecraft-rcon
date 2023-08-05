package io.graversen.minecraft.rcon.util;

public class WhiteListMode {

    public interface Value {
        String getModeName();
    }

    public enum Targeted implements Value {
        ADD,
        REMOVE;

        public String getModeName() {
            return name().toLowerCase();
        }
    }

    public enum Management implements Value {
        LIST,
        OFF,
        ON,
        RELOAD;

        public String getModeName() {
            return name().toLowerCase();
        }
    }
}
