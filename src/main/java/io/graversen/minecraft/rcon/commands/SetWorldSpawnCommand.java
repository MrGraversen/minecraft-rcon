package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Position;

import java.util.Objects;

public class SetWorldSpawnCommand implements ICommand {
    private final Position position;
    private final int angle;

    public SetWorldSpawnCommand(Position position, int angle) {
        this.position = Objects.requireNonNull(position);
        this.angle = angle;
    }

    public SetWorldSpawnCommand(Position position) {
        this(position, 0);
    }

    @Override
    public String command() {
        return "setworldspawn " + position + " " + angle;
    }
}
