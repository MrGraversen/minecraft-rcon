package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;
import io.graversen.minecraft.rcon.util.WhiteListMode;

import java.util.Objects;

public class WhiteListCommand extends BaseTargetedCommand {
    private final WhiteListMode.Value whiteListMode;

    public WhiteListCommand(Target target, WhiteListMode.Targeted whiteListMode) {
        super(target);
        this.whiteListMode = Objects.requireNonNull(whiteListMode);
    }

    public WhiteListCommand(WhiteListMode.Management whiteListMode) {
        super(null);
        this.whiteListMode = Objects.requireNonNull(whiteListMode);
    }

    public WhiteListMode.Value getWhiteListMode() {
        return whiteListMode;
    }

    @Override
    public String command() {
        return switch (whiteListMode) {
            case WhiteListMode.Targeted targeted -> "whitelist " + targeted.getModeName() + " " + getTarget();
            case WhiteListMode.Management management -> "whitelist " + management.getModeName();
            default -> throw new IllegalStateException("Unexpected value: " + whiteListMode);
        };
    }
}
