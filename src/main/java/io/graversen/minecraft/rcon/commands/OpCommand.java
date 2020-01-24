package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;

public class OpCommand extends BaseTargetedCommand {
    public OpCommand(Target target) {
        super(target);
    }

    @Override
    public String command() {
        return "op " + getTarget();
    }
}
