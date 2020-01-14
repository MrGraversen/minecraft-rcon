package io.graversen.minecraft.rcon.commands.tellraw;

import io.graversen.minecraft.rcon.JsonUtils;
import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public class TellRawCommand extends BaseTargetedCommand {
    private final String text;
    private final boolean bold;
    private final boolean italic;
    private final boolean underlined;
    private final boolean striketrough;
    private final boolean obfuscated;
    private final String color;
    private final ClickEvent clickEvent;
    private final HoverEvent hoverEvent;
    private final TellRawCommand[] extra;

    public TellRawCommand(String target, String text, boolean bold, boolean italic) {
        super(target);
        this.text = text;
        this.bold = bold;
        this.italic = italic;
        this.underlined = false;
        this.striketrough = false;
        this.obfuscated = false;
        this.clickEvent = null;
        this.hoverEvent = null;
        this.color = null;
        this.extra = null;
    }

    public TellRawCommand(String target, String text, String color, boolean bold, boolean italic) {
        super(target);
        this.text = text;
        this.bold = bold;
        this.italic = italic;
        this.underlined = false;
        this.striketrough = false;
        this.obfuscated = false;
        this.clickEvent = null;
        this.hoverEvent = null;
        this.color = color;
        this.extra = null;
    }

    public TellRawCommand(String target, String text, boolean bold, boolean italic, boolean underlined, boolean striketrough, boolean obfuscated, String color, ClickEvent clickEvent, HoverEvent hoverEvent, TellRawCommand[] extra) {
        super(target);
        this.text = text;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.striketrough = striketrough;
        this.obfuscated = obfuscated;
        this.color = color;
        this.clickEvent = clickEvent;
        this.hoverEvent = hoverEvent;
        this.extra = extra;
    }

    public String getText() {
        return text;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public boolean isStriketrough() {
        return striketrough;
    }

    public boolean isObfuscated() {
        return obfuscated;
    }

    public String getColor() {
        return color;
    }

    public ClickEvent getClickEvent() {
        return clickEvent;
    }

    public HoverEvent getHoverEvent() {
        return hoverEvent;
    }

    public TellRawCommand[] getExtra() {
        return extra;
    }

    @Override
    public String command() {
        final var variables = Map.of(
                "target", getTarget(),
                "rawJson", JsonUtils.toJson(this)
        );

        return StringSubstitutor.replace("tellraw ${target} ${rawJson}", variables);
    }
}
