package io.graversen.minecraft.rcon.commands.execute;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class ExecuteCommand implements ICommand {
    private final ICommand wrappedCommand;

    ExecuteCommand(ICommand wrappedCommand) {
        this.wrappedCommand = wrappedCommand;
    }

    @Override
    public String command() {
        return wrappedCommand.command();
    }
}
