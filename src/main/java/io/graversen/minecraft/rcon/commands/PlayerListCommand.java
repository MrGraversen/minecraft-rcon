package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class PlayerListCommand implements ICommand {
    @Override
    public String command() {
        return "list uuids";
    }
}
