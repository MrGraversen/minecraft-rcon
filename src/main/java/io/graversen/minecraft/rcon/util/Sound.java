package io.graversen.minecraft.rcon.util;

public class Sound {
    private final String namespace;
    private final String sound;

    public Sound(String namespace, String sound) {
        this.namespace = namespace;
        this.sound = sound;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", namespace, sound);
    }
}
