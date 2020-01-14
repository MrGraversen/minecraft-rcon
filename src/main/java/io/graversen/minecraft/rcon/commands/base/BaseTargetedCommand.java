package io.graversen.minecraft.rcon.commands.base;

import java.util.Objects;

public abstract class BaseTargetedCommand implements ICommand {
    private transient final String target;

    public BaseTargetedCommand(String target) {
        Objects.requireNonNull(target);
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}
