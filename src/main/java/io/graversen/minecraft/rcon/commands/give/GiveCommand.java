package io.graversen.minecraft.rcon.commands.give;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;
import java.util.Objects;

public class GiveCommand extends BaseTargetedCommand {
    private final String item;
    private final String nbt;
    private final int count;

    public GiveCommand(String target, String item, String nbt, int count) {
        super(target);
        this.item = Objects.requireNonNull(item);
        this.nbt = Objects.requireNonNullElse(nbt, "");
        this.count = count;
    }

    public String getItem() {
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
