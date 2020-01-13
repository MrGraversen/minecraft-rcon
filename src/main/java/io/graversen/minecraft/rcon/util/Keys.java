package io.graversen.minecraft.rcon.util;

public enum Keys {
    FORWARD,
    LEFT,
    BACK,
    RGIHT,
    JUMP,
    SNEAK,
    SPRINT,
    INVENTORY,
    DROP,
    USE,
    ATTACK,
    CHAT,
    SCREENSHOT,
    HOTBAR_1,
    HOTBAR_2,
    HOTBAR_3,
    HOTBAR_4,
    HOTBAR_5,
    HOTBAR_6,
    HOTBAR_7,
    HOTBAR_8,
    HOTBAR_9;

    public String getKeyCode() {
        return String.format("key.%s", name().toLowerCase().replaceAll("_", "."));
    }
}
