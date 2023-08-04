package io.graversen.minecraft.rcon.util;

public enum HoverEventAction {
    SHOW_TEXT,
    SHOW_ITEM,
    SHOW_ENTITY;

    private String getCommandName() {
        return name().toLowerCase();
    }
}
