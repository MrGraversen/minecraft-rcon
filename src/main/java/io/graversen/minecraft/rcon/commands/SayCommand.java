package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public record SayCommand(String text) implements ICommand {

    @Override
    public String command() {
        return "say " + text();
    }
}
