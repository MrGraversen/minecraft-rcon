package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;

public class OpCommand extends BaseTargetedCommand {
    public OpCommand(String target) {
        super(target);
    }

    @Override
    public String command() {
        return "op " + getTarget();
    }
}
