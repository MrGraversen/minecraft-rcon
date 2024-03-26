package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public record DifficultyCommand(String difficulty) implements ICommand {

    @Override
    public String command() {
        return "difficulty " + difficulty();
    }
}
