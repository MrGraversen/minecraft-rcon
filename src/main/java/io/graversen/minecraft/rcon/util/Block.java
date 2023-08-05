package io.graversen.minecraft.rcon.util;

public class Block {
    private final String namespace;
    private final String block;

    public Block(String namespace, String block) {
        this.namespace = namespace;
        this.block = block;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", namespace, block);
    }
}
