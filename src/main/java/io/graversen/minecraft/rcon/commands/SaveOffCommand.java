package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class SaveOffCommand implements ICommand {
    @Override
    public String command() {
        return "save-off";
    }
}
