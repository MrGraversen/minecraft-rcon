package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class ReloadCommand implements ICommand {
    @Override
    public String command() {
        return "reload";
    }
}
