package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;
import io.graversen.minecraft.rcon.util.WhiteListModes;

import java.util.Objects;

public class WhiteListCommand extends BaseTargetedCommand {
    private final WhiteListModes whiteListMode;

    public WhiteListCommand(Target target, WhiteListModes whiteListMode) {
        super(target);
        this.whiteListMode = Objects.requireNonNull(whiteListMode);
    }

    public WhiteListModes getWhiteListMode() {
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
