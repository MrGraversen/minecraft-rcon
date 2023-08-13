package io.graversen.minecraft.rcon.util;

public class Advancement {
    private final String namespace;
    private final String advancement;

    public Advancement(String namespace, String advancement) {
        this.namespace = namespace;
        this.advancement = advancement;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", namespace, advancement);
    }
}
