package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class RideCommand implements ICommand {
    private final String command;

    private RideCommand(String command) {
        this.command = command;
    }

    public static RideCommand mount(Target target, String vehicle) {
        return new RideCommand(Objects.requireNonNull(target) + " mount " + Objects.requireNonNull(vehicle));
    }

    public static RideCommand dismount(Target target) {
        return new RideCommand(Objects.requireNonNull(target) + " dismount");
    }

    @Override
    public String command() {
        return "ride " + command;
    }
}
