package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class JfrCommand implements ICommand {
    private final boolean start;

    private JfrCommand(boolean start) {
        this.start = start;
    }

    public static JfrCommand start() {
        return new JfrCommand(true);
    }

    public static JfrCommand stop() {
        return new JfrCommand(false);
    }

    @Override
    public String command() {
        return "jfr " + (start ? "start" : "stop");
    }
}
