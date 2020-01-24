package io.graversen.minecraft.rcon.commands.base;

import io.graversen.minecraft.rcon.util.Position;

import java.util.Objects;

public abstract class Base3DPositionalCommand implements ICommand {
    private final Position position1;
    private final Position position2;

    public Base3DPositionalCommand(Position position1, Position position2) {
        this.position1 = position1;
        this.position2 = position2;
    }

    public Position getPosition1() {
        return position1;
    }

    public Position getPosition2() {
        return position2;
    }
}
