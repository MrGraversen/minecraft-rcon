package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

import java.util.Objects;

public class DebugCommand implements ICommand {
    private final String command;
    private final String function;

    private DebugCommand(String command, String function) {
        this.command = command;
        this.function = function;
    }

    public static DebugCommand start() {
        return new DebugCommand("start", null);
    }

    public static DebugCommand stop() {
        return new DebugCommand("stop", null);
    }

    public static DebugCommand function(String function) {
        return new DebugCommand("function", Objects.requireNonNull(function));
    }

    @Override
    public String command() {
        return "debug " + switch (command) {
            case "start", "stop" -> command;
            case "function" -> (command + " " + function).trim();
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }
}
