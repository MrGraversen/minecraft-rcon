package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class SpawnPointCommand extends BaseTargetedCommand {
    private final Position position;

    public SpawnPointCommand(Target target, Position position) {
        super(target);
        this.position = Objects.requireNonNull(position);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String command() {
        return "spawnpoint " + getTarget() + " " + getPosition();
    }
}
