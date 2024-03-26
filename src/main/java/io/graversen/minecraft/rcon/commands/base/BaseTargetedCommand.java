package io.graversen.minecraft.rcon.commands.base;

import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public abstract class BaseTargetedCommand implements ICommand {
    private final transient Target target;

    public BaseTargetedCommand(Target target) {
        this.target = Objects.requireNonNull(target);
    }

    public Target getTarget() {
        return target;
    }
}
