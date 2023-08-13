package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;

public class KillCommand extends BaseTargetedCommand {
    public KillCommand(Target target) {
        super(target);
    }

    @Override
    public String command() {
        return "kill " + getTarget();
    }
}
