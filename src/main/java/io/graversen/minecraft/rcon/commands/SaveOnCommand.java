package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class SaveOnCommand implements ICommand {
    @Override
    public String command() {
        return "save-on";
    }
}
