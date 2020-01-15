package io.graversen.minecraft.rcon.commands.tellraw;

import io.graversen.minecraft.rcon.commands.base.ICommandBuilder;
import io.graversen.minecraft.rcon.commands.base.ITargetingCommandBuilder;
import io.graversen.minecraft.rcon.util.ClickEventActions;
import io.graversen.minecraft.rcon.util.Colors;
import io.graversen.minecraft.rcon.util.HoverEventActions;
import io.graversen.minecraft.rcon.util.Selectors;

public class TellRawCommandBuilder implements ITargetingCommandBuilder<TellRawCommandBuilder>, ICommandBuilder<TellRawCommand> {
    private String target;
    private String text;
    private boolean bold;
    private boolean italic;
    private boolean underlined;
    private boolean striketrough;
    private boolean obfuscated;
    private String color;
    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;
    private TellRawCommand[] extra;

    @Override
    public TellRawCommandBuilder targeting(String playerName) {
        this.target = playerName;
        return this;
    }

    @Override
    public TellRawCommandBuilder targeting(Selectors usingSelector) {
        this.target = usingSelector.getSelectorString();
        return this;
    }

    public TellRawCommandBuilder withColor(Colors color) {
        this.color = color.getColorName();
        return this;
    }

    public TellRawCommandBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public TellRawCommandBuilder bold() {
        this.bold = true;
        return this;
    }

    public TellRawCommandBuilder italic() {
        this.italic = true;
        return this;
    }

    public TellRawCommandBuilder underlined() {
        this.underlined = true;
        return this;
    }

    public TellRawCommandBuilder strikethrough() {
        this.striketrough = true;
        return this;
    }

    public TellRawCommandBuilder obfuscated() {
        this.obfuscated = true;
        return this;
    }

    public TellRawCommandBuilder withExtras(TellRawCommand... tellRawCommands) {
        this.extra = tellRawCommands;
        return this;
    }

    public TellRawCommandBuilder withClickEvent(ClickEventActions clickEventAction, String value) {
        this.clickEvent = new ClickEvent(clickEventAction.name().toLowerCase(), value);
        return this;
    }

    public TellRawCommandBuilder withHoverEvent(HoverEventActions hoverEventAction, String value) {
        this.hoverEvent = new HoverEvent(hoverEventAction.name().toLowerCase(), value);
        return this;
    }

    @Override
    public boolean validate() {
        return (target != null) && (text != null);
    }

    @Override
    public TellRawCommand build() {
        if (validate()) {
            return new TellRawCommand(target, text, bold, italic, underlined, striketrough, obfuscated, color, clickEvent, hoverEvent, extra);
        } else {
            throw new IllegalArgumentException("Could not construct valid TellRaw Command");
        }
    }
}
