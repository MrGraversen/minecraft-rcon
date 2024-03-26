package io.graversen.minecraft.rcon.commands.tellraw;

import io.graversen.minecraft.rcon.JsonUtils;
import io.graversen.minecraft.rcon.commands.base.ICommand;

import java.util.List;

public record TellRawCompositeCommand(List<TellRawCommand> tellRawCommands) implements ICommand {

    @Override
    public String command() {
        if (tellRawCommands.isEmpty()) throw new IllegalStateException("Cannot send empty TellRawCompositeCommand");

        return "tellraw " + tellRawCommands().get(0).getTarget() + " " + JsonUtils.toJson(tellRawCommands());
    }
}
