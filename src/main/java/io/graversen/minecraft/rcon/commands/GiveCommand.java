package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Item;
import io.graversen.minecraft.rcon.util.Target;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;
import java.util.Objects;

public class GiveCommand extends BaseTargetedCommand {
    private final Item item;
    private final String nbt;
    private final int count;

    public GiveCommand(Target target, Item item, String nbt, int count) {
        super(target);
        this.item = Objects.requireNonNull(item);
        this.nbt = Objects.requireNonNullElse(nbt, "");
        this.count = count <= 0 ? 1 : count;
    }

    public Item getItem() {
        return item;
    }

    public String getNbt() {
        return nbt;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String command() {
        final var variables = Map.of(
                "target", getTarget(),
                "item", getItem(),
                "nbt", getNbt(),
                "count", getCount()
        );

        return StringSubstitutor.replace("give ${target} ${item}${nbt} ${count}", variables);
    }
}
