package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class BanCommand extends BaseTargetedCommand {
    private final String reason;

    public BanCommand(Target target, String reason) {
        super(target);
        this.reason = Objects.requireNonNullElse(reason, "");
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String command() {
        return ("ban " + getTarget() + " " + getReason()).trim();
    }
}
