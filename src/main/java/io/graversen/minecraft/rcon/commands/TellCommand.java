package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Arrays;

public class TellCommand implements ICommand {
    private final Target[] targets;
    private final String message;

    public TellCommand(Target[] targets, String message) {
        this.targets = targets;
        this.message = message;
    }

    @Override
    public String command() {
        return "tell " + String.join(" ", Arrays.stream(targets).map(Target::toString).toArray(String[]::new)) + " " + message;
    }
}
