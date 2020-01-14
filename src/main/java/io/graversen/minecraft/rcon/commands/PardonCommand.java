package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;

public class PardonCommand extends BaseTargetedCommand {
    public PardonCommand(String target) {
        super(target);
    }

    @Override
    public String command() {
        return "pardon " + getTarget();
    }
}
