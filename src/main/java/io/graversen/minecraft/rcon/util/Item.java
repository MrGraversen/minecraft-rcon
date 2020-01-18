package io.graversen.minecraft.rcon.util;

public class Item {
    private final String namespace;
    private final String item;

    public Item(String namespace, String item) {
        this.namespace = namespace;
        this.item = item;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", namespace, item);
    }
}
