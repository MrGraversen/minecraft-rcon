package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class LootCommand implements ICommand {
    public class LootTarget {
        private LootTarget() {}

        public final String give(Target target) {
            return "give " + Objects.requireNonNull(target);
        }

        public final String insert(Position position) {
            return "insert " + Objects.requireNonNull(position);
        }

        public final String spawn(Position position) {
            return "spawn " + Objects.requireNonNull(position);
        }

        public final String replaceBlock(Position position, String slot, int count) {
            return "replace block "
                    + Objects.requireNonNull(position) + " "
                    + Objects.requireNonNull(slot)
                    + (count > 0 ? " " + count : "");
        }

        public final String replaceEntity(Target target, String slot, int count) {
            return "replace entity "
                    + Objects.requireNonNull(target) + " "
                    + Objects.requireNonNull(slot)
                    + (count > 0 ? " " + count : "");
        }
    }

    public static class LootSource {
        private LootSource() {}

        public final String fishing(String lootTable, Position position, String tool) {
            return ("fishing "
                    + Objects.requireNonNull(lootTable) + " "
                    + Objects.requireNonNull(position) + " "
                    + (tool == null ? "" : tool)).trim();
        }

        public final String loot(String lootTable) {
            return "loot " + Objects.requireNonNull(lootTable);
        }

        public final String kill(Target target) {
            return "kill " + Objects.requireNonNull(target);
        }

        public final String mine(Position position, String tool) {
            return "mine " + Objects.requireNonNull(position) + " " + (tool == null ? "" : tool);
        }
    }

    private final String[] arguments;

    public LootCommand(LootTarget target, LootSource source) {
        arguments = new String[] {target.toString(), source.toString() };
    }

    @Override
    public String command() {
        return "loot " + String.join(" ", arguments);
    }
}
