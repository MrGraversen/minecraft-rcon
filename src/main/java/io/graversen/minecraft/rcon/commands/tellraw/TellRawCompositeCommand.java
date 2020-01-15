package io.graversen.minecraft.rcon.commands.tellraw;

import io.graversen.minecraft.rcon.JsonUtils;
import io.graversen.minecraft.rcon.commands.base.ICommand;
import org.apache.commons.text.StringSubstitutor;

import java.util.List;
import java.util.Map;

public class TellRawCompositeCommand implements ICommand {
    private final List<TellRawCommand> tellRawCommands;

    public TellRawCompositeCommand(List<TellRawCommand> tellRawCommands) {
        this.tellRawCommands = tellRawCommands;
    }

    public List<TellRawCommand> getTellRawCommands() {
        return tellRawCommands;
    }

    @Override
    public String command() {
        if (tellRawCommands.isEmpty()) throw new IllegalStateException("Cannot send empty TellRawCompositeCommand");

        final var variables = Map.of(
                "target", getTellRawCommands().get(0).getTarget(),
                "rawJson", JsonUtils.toJson(getTellRawCommands())
        );

        return StringSubstitutor.replace("tellraw ${target} ${rawJson}", variables);
    }
}
