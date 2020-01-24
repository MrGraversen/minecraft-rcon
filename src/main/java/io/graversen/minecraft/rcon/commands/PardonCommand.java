package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;

public class PardonCommand extends BaseTargetedCommand {
    public PardonCommand(Target target) {
        super(target);
    }

    @Override
    public String command() {
        return "pardon " + getTarget();
    }
}
