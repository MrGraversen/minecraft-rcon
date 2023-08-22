package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class SaveAllCommand implements ICommand {
    private final boolean flush;

    public SaveAllCommand(boolean flush) {
        this.flush = flush;
    }

    @Override
    public String command() {
        return ("save-all " + (flush ? "flush" : "")).trim();
    }
}
