package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;
import io.graversen.minecraft.rcon.util.WhiteListMode;

import java.util.Objects;

public class WhiteListCommand extends BaseTargetedCommand {
    private final WhiteListMode whiteListMode;

    public WhiteListCommand(Target target, WhiteListMode whiteListMode) {
        super(target);
        this.whiteListMode = Objects.requireNonNull(whiteListMode);
    }

    public WhiteListMode getWhiteListMode() {
        return whiteListMode;
    }

    @Override
    public String command() {
        return switch (getWhiteListMode()) {
            case ADD, REMOVE -> "whitelist " + getWhiteListMode().getModeName() + " " + getTarget();
            case LIST, OFF, ON, RELOAD -> "whitelist " + getWhiteListMode().getModeName();
        };
    }
}
