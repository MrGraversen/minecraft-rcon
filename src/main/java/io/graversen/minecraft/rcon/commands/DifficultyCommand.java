package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseCommand;

public class DifficultyCommand extends BaseCommand {
    private final String difficulty;

    public DifficultyCommand(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
