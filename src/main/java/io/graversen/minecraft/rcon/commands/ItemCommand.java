package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Arrays;
import java.util.Objects;

public class ItemCommand implements ICommand {
    public static class ItemTarget {
        private ItemTarget() {}

        public static String block(Position target) {
            return "block " + target;
        }

        public static String entity(Target target) {
            return "entity " + target;
        }
    }

    private final ItemTarget target;
    private final String slot;
    private final String[] args;

    private ItemCommand(ItemTarget target, String slot, String[] args) {
        this.target = Objects.requireNonNull(target);
        this.slot = Objects.requireNonNull(slot);
        this.args = args;
    }

    public static ItemCommand modify(ItemTarget target, String slot, String modifier) {
        return new ItemCommand(target, slot, new String[] { Objects.requireNonNull(modifier) });
    }

    public static ItemCommand replaceWith(ItemTarget target, String slot, String item) {
        return ItemCommand.replaceWith(target, slot, item, -1);
    }

    public static ItemCommand replaceWith(ItemTarget target, String slot, String item, int count) {
        return new ItemCommand(target, slot, Arrays.stream(new String[] {
                "with", Objects.requireNonNull(item), count > 0 ? String.valueOf(count) : null
        }).filter(Objects::nonNull).toArray(String[]::new));
    }

    public static ItemCommand replaceFrom(ItemTarget target, String slot, ItemTarget sourceTarget, String sourceSlot) {
        return new ItemCommand(target, slot, new String[] { "from", Objects.requireNonNull(sourceTarget).toString(), Objects.requireNonNull(sourceSlot) });
    }

    @Override
    public String command() {
        return "item " + target + " " + slot + " " + String.join(" ", args);
    }
}
