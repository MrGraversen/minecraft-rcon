package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Arrays;

public class TagCommand implements ICommand {
    private final String command;

    private TagCommand(String command) {
        this.command = command;
    }

    public static TagCommand add(Target[] targets, String tag) {
        return new TagCommand(String.join(" ", Arrays.stream(targets).map(Target::toString).toArray(String[]::new)) + " add " + tag);
    }

    public static TagCommand list(Target[] targets) {
        return new TagCommand(String.join(" ", Arrays.stream(targets).map(Target::toString).toArray(String[]::new)) + " list");
    }

    public static TagCommand remove(Target[] targets, String tag) {
        return new TagCommand(String.join(" ", Arrays.stream(targets).map(Target::toString).toArray(String[]::new)) + " remove " + tag);
    }

    @Override
    public String command() {
        return "tag " + command;
    }
}
