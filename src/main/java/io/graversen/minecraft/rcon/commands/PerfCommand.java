package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class PerfCommand implements ICommand {
    private final boolean start;

    private PerfCommand(boolean start) {
        this.start = start;
    }

    public static PerfCommand start() {
        return new PerfCommand(true);
    }

    public static PerfCommand stop() {
        return new PerfCommand(false);
    }

    @Override
    public String command() {
        return "jfr " + (start ? "start" : "stop");
    }
}
