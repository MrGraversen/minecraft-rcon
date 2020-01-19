package io.graversen.minecraft.rcon.commands.execute;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.commands.base.INonTargetedCommand;
import io.graversen.minecraft.rcon.util.Selectors;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public class ExecuteCommandBuilders {
    private ExecuteCommandBuilders() {

    }

    public static ExecuteAtCommandBuilder executeAt(String playerName) {
        return new ExecuteAtCommandBuilder(playerName);
    }

    public static ExecuteAtCommandBuilder executeAt(Selectors selector) {
        return new ExecuteAtCommandBuilder(selector.getSelectorString());
    }

    public static class ExecuteAtCommandBuilder {
        private static final String EXECUTE_AT_COMMAND_TEMPLATE = "execute at ${target} run ${command}";
        private final String target;

        ExecuteAtCommandBuilder(String target) {
            this.target = target;
        }

        public String getTarget() {
            return target;
        }

        public ExecuteCommand run(INonTargetedCommand command) {
            final String compiledCommand = StringSubstitutor.replace(
                    EXECUTE_AT_COMMAND_TEMPLATE,
                    Map.of(
                            "target", getTarget(),
                            "command", command.command()
                    )
            );

            return new ExecuteCommand(() -> compiledCommand);
        }
    }
}
