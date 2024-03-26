package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BasePositionalCommand;
import io.graversen.minecraft.rcon.util.Position;

import java.util.Objects;

public class SummonCommand extends BasePositionalCommand {
    private final String entityName;
    private final String nbt;

    public SummonCommand(Position position, String entityName, String nbt) {
        super(position);
        this.entityName = Objects.requireNonNull(entityName);
        this.nbt = Objects.requireNonNullElse(nbt, "");
    }

    public String getEntityName() {
        return entityName;
    }

    public String getNbt() {
        return nbt;
    }

    @Override
    public String command() {
        return "summon " + getEntityName() + " " + getPosition() + " " + getNbt();
    }
}
