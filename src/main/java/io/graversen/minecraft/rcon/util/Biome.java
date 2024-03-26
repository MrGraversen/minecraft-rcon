package io.graversen.minecraft.rcon.util;

public class Biome {
    private final String namespace;
    private final String biome;

    public Biome(String namespace, String biome) {
        this.namespace = namespace;
        this.biome = biome;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", namespace, biome);
    }
}
