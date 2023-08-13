package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class SetIdleTimeoutCommand implements ICommand {
    private final int timeout;

    public SetIdleTimeoutCommand(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String command() {
        return "setidletimeout " + timeout;
    }
}
