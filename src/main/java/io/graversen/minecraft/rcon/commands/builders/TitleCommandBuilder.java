package io.graversen.minecraft.rcon.commands.builders;

import io.graversen.minecraft.rcon.commands.builders.interfaces.ICommandBuilder;
import io.graversen.minecraft.rcon.commands.builders.interfaces.ITargetingCommandBuilder;
import io.graversen.minecraft.rcon.commands.objects.TitleCommand;
import io.graversen.minecraft.rcon.util.Colors;
import io.graversen.minecraft.rcon.util.Selectors;
import io.graversen.minecraft.rcon.util.TitlePositions;

public class TitleCommandBuilder implements ITargetingCommandBuilder<TitleCommandBuilder>, ICommandBuilder<TitleCommand> {
    private String target;
    private String position;
    private String text;
    private boolean bold;
    private boolean italic;
    private boolean underlined;
    private boolean striketrough;
    private boolean obfuscated;
    private String color;

    @Override
    public TitleCommandBuilder targeting(String playerName) {
        this.target = playerName;
        return this;
    }

    @Override
    public TitleCommandBuilder targeting(Selectors usingSelector) {
        this.target = usingSelector.getSelectorString();
        return this;
    }

    public TitleCommandBuilder atPosition(TitlePositions titlePosition) {
        this.position = titlePosition.getTitlePositionValue();
        return this;
    }

    public TitleCommandBuilder withColor(Colors color) {
        this.color = color.getColorName();
        return this;
    }

    public TitleCommandBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public TitleCommandBuilder bold() {
        this.bold = true;
        return this;
    }

    public TitleCommandBuilder italic() {
        this.italic = true;
        return this;
    }

    public TitleCommandBuilder underlined() {
        this.underlined = true;
        return this;
    }

    public TitleCommandBuilder strikethrough() {
        this.striketrough = true;
        return this;
    }

    public TitleCommandBuilder obfuscated() {
        this.obfuscated = true;
        return this;
    }

    @Override
    public boolean validate() {
        return (target != null) && (text != null) && (position != null);
    }

    @Override
    public TitleCommand build() {
        if (validate()) {
            return new TitleCommand(target, text, position, bold, italic, underlined, striketrough, obfuscated, color);
        } else {
            throw new IllegalArgumentException("Could not construct valid Title Command");
        }
    }
}
