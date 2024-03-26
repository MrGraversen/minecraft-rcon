package io.graversen.minecraft.rcon.util;

public class Structure {
    private final String namespace;
    private final String structure;

    public Structure(String namespace, String structure) {
        this.namespace = namespace;
        this.structure = structure;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", namespace, structure);
    }
}
