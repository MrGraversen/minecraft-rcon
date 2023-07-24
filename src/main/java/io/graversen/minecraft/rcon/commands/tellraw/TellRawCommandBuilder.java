package io.graversen.minecraft.rcon.commands.tellraw;

import io.graversen.minecraft.rcon.commands.base.ICommandBuilder;
import io.graversen.minecraft.rcon.commands.base.ITargetingCommandBuilder;
import io.graversen.minecraft.rcon.util.*;

public class TellRawCommandBuilder implements ITargetingCommandBuilder<TellRawCommandBuilder>, ICommandBuilder<TellRawCommand> {
    private Target target;
    private String text;
    private boolean bold;
    private boolean italic;
    private boolean underlined;
    private boolean strikethrough;
    private boolean obfuscated;
    private String color;
    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;

    @Override
    public TellRawCommandBuilder targeting(String playerName) {
        this.target = Target.player(playerName);
        return this;
    }

    @Override
    public TellRawCommandBuilder targeting(Selectors usingSelector) {
        this.target = Target.selector(usingSelector);
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
        this.strikethrough = true;
        return this;
    }

    public TellRawCommandBuilder obfuscated() {
        this.obfuscated = true;
        return this;
    }

    public TellRawCommandBuilder withClickEvent(ClickEventActions clickEventAction, String value) {
        this.clickEvent = new ClickEvent(clickEventAction.name().toLowerCase(), value);
        return this;
    }

    public TellRawCommandBuilder withHoverTextEvent(TellRawCommand tellRawCommand) {
        this.hoverEvent = new HoverEvent(HoverEventActions.SHOW_TEXT.name().toLowerCase(), new TextContent[]{tellRawCommand.toTextContent()});
        return this;
    }

    @Override
    public boolean validate() {
        return (target != null) && (text != null);
    }

    @Override
    public TellRawCommand build() {
        if (validate()) {
            return new TellRawCommand(target, text, bold, italic, underlined, strikethrough, obfuscated, color, clickEvent, hoverEvent);
        } else {
            throw new IllegalArgumentException("Could not construct valid TellRaw Command");
        }
    }
}
