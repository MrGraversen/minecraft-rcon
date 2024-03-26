package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class SpectateCommand implements ICommand {
    private final String command;

    private SpectateCommand(String command) {
        this.command = command;
    }

    public static SpectateCommand spectate(Target target, Target player) {
        return new SpectateCommand(Objects.requireNonNull(target) + " " + Objects.requireNonNullElse(player, ""));
    }

    public static SpectateCommand stopSpectating() {
        return new SpectateCommand("");
    }

    @Override
    public String command() {
        return "spectate " + command.trim();
    }
}
