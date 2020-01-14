package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;

public class DeOpCommand extends BaseTargetedCommand {
    public DeOpCommand(String target) {
        super(target);
    }

    @Override
    public String command() {
        return "deop " + getTarget();
    }
}
