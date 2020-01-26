package io.graversen.minecraft.rcon.commands.execute;

import io.graversen.minecraft.rcon.commands.base.BasePositionalCommand;
import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Dimensions;
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

    public static ExecuteInCommandBuilder executeIn(Dimensions dimension) {
        return new ExecuteInCommandBuilder(dimension);
    }

    public static class ExecuteAtCommandBuilder {
        private final String target;

        ExecuteAtCommandBuilder(String target) {
            this.target = target;
        }

        public String getTarget() {
            return target;
        }

        public ExecuteCommand run(ICommand command) {
            final String compiledCommand = StringSubstitutor.replace(
                    "execute at ${target} run ${command}",
                    Map.of(
                            "target", getTarget(),
                            "command", command.command()
                    )
            );

            return new ExecuteCommand(() -> compiledCommand);
        }
    }

    public static class ExecuteInCommandBuilder {
        private final Dimensions dimension;

        ExecuteInCommandBuilder(Dimensions dimension) {
            this.dimension = dimension;
        }

        public Dimensions getDimension() {
            return dimension;
        }

        public ExecuteCommand run(ICommand command) {
            final String compiledCommand = StringSubstitutor.replace(
                    "execute in ${dimension} run ${command}",
                    Map.of(
                            "dimension", getDimension().getNamespacedDimensionString(),
                            "command", command.command()
                    )
            );

            return new ExecuteCommand(() -> compiledCommand);
        }
    }

}
