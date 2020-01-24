package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;

public class DeOpCommand extends BaseTargetedCommand {
    public DeOpCommand(Target target) {
        super(target);
    }

    @Override
    public String command() {
        return "deop " + getTarget();
    }
}
