package io.graversen.minecraft.rcon.commands.title;

import io.graversen.minecraft.rcon.JsonUtils;
import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;

public class TitleCommand extends BaseTargetedCommand {
    private final transient String position;
    private final String text;
    private final boolean bold;
    private final boolean italic;
    private final boolean underlined;
    private final boolean strikethrough;
    private final boolean obfuscated;
    private final String color;

    TitleCommand(
            Target target,
            String text,
            String position,
            boolean bold,
            boolean italic,
            boolean underlined,
            boolean strikethrough,
            boolean obfuscated,
            String color
    ) {
        super(target);
        this.text = text;
        this.position = position;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.strikethrough = strikethrough;
        this.obfuscated = obfuscated;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public String getPosition() {
        return position;
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

    public boolean isStrikethrough() {
        return strikethrough;
    }

    public boolean isObfuscated() {
        return obfuscated;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String command() {
        return "title " + getTarget() + " " + getPosition() + " " + JsonUtils.toJson(this);
    }
}
