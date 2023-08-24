package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Target;
import io.graversen.minecraft.rcon.util.WhiteListMode;

import java.util.Objects;

public class WhiteListCommand implements ICommand {
    private final Target target;
    private final WhiteListMode.Value whiteListMode;

    public WhiteListCommand(Target target, WhiteListMode.Targeted whiteListMode) {
        this.target = Objects.requireNonNull(target);
        this.whiteListMode = Objects.requireNonNull(whiteListMode);
    }

    public WhiteListCommand(WhiteListMode.Management whiteListMode) {
        this.target = null;
        this.whiteListMode = Objects.requireNonNull(whiteListMode);
    }

    public WhiteListMode.Value getWhiteListMode() {
        return whiteListMode;
    }

    @Override
    public String command() {
        return switch (whiteListMode) {
            case WhiteListMode.Targeted targeted -> "whitelist " + targeted.getModeName() + " " + target;
            case WhiteListMode.Management management -> "whitelist " + management.getModeName();
            default -> throw new IllegalStateException("Unexpected value: " + whiteListMode);
        };
    }
}
