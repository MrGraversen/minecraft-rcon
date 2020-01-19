package io.graversen.minecraft.rcon.commands.base;

import io.graversen.minecraft.rcon.util.Position;

import java.util.Objects;

public abstract class BasePositionalCommand implements ICommand {
    private final Position position;

    public BasePositionalCommand(Position position) {
        this.position = Objects.requireNonNull(position);
    }

    public Position getPosition() {
        return position;
    }
}
