package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

import java.util.Objects;

public class FunctionCommand implements ICommand {
    private final String command;

    public FunctionCommand(String name, String... args) {
        command = Objects.requireNonNull(name) + " " + String.join(" ", args);
    }

    @Override
    public String command() {
        return "function " + command;
    }
}
