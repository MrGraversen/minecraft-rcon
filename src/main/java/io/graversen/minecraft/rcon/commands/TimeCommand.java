package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class TimeCommand implements ICommand {
    private final int time;

    public TimeCommand(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String command() {
        return "time set " + getTime();
    }
}
