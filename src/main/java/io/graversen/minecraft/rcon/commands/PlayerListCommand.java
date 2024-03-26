package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class PlayerListCommand implements ICommand {
    private final boolean uuids;

    private PlayerListCommand(boolean uuids) {
        this.uuids = uuids;
    }

    public static PlayerListCommand uuids() {
        return new PlayerListCommand(true);
    }

    public static PlayerListCommand names() {
        return new PlayerListCommand(false);
    }

    @Override
    public String command() {
        return uuids ? "list uuids" : "list";
    }
}
