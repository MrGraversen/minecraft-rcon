package io.graversen.minecraft.rcon.commands.builders;

import io.graversen.minecraft.rcon.commands.builders.interfaces.ICommandBuilder;
import io.graversen.minecraft.rcon.commands.builders.interfaces.ITargetingCommandBuilder;
import io.graversen.minecraft.rcon.commands.objects.GiveCommand;
import io.graversen.minecraft.rcon.util.Selectors;

public class GiveCommandBuilder implements ITargetingCommandBuilder<GiveCommandBuilder>, ICommandBuilder<GiveCommand> {
    private String target;
    private String item;
    private int amount;
    private int data;
    private String dataTag;

    @Override
    public GiveCommandBuilder targeting(String playerName) {
        this.target = playerName;
        return this;
    }

    @Override
    public GiveCommandBuilder targeting(Selectors usingSelector) {
        this.target = usingSelector.getSelectorString();
        return this;
    }

    public GiveCommandBuilder withItem(String item) {
        this.item = item;
        return this;
    }

    public GiveCommandBuilder withItem(String namespace, String item) {
        this.item = String.format("%s:%s", namespace, item);
        return this;
    }

    public GiveCommandBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public GiveCommandBuilder withData(int data) {
        this.data = data;
        return this;
    }

    public GiveCommandBuilder withDataTag(String dataTag) {
        this.dataTag = dataTag;
        return this;
    }

    @Override
    public boolean validate() {
        return (target != null) && (item != null);
    }

    @Override
    public GiveCommand build() {
        if (validate()) {
            return new GiveCommand(target, item, amount, data, dataTag);
        } else {
            throw new IllegalArgumentException("Could not construct valid Give Command");
        }
    }
}
