package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Target;

public class TeleportCommand extends BaseTargetedCommand {
    private final String destination;

    public TeleportCommand(Target target, Target destination) {
        super(target);
        this.destination = destination.toString();
    }

    public TeleportCommand(Target target, Position destination) {
        super(target);
        this.destination = destination.toString();
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String command() {
        return "tp " + getTarget() + " " + getDestination();
    }
}
