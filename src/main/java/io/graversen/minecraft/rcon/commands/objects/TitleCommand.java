package io.graversen.minecraft.rcon.commands.objects;

import io.graversen.minecraft.rcon.commands.objects.base.BaseTargetedCommand;

public class TitleCommand extends BaseTargetedCommand
{
    private transient final String position;
    private final String text;
    private final boolean bold;
    private final boolean italic;
    private final boolean underlined;
    private final boolean striketrough;
    private final boolean obfuscated;
    private final String color;

    public TitleCommand(String target, String text, String position, boolean bold, boolean italic, boolean underlined, boolean striketrough, boolean obfuscated, String color)
    {
        super(target);
        this.text = text;
        this.position = position;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.striketrough = striketrough;
        this.obfuscated = obfuscated;
        this.color = color;
    }

    public String getText()
    {
        return text;
    }

    public String getPosition()
    {
        return position;
    }

    public boolean isBold()
    {
        return bold;
    }

    public boolean isItalic()
    {
        return italic;
    }

    public boolean isUnderlined()
    {
        return underlined;
    }

    public boolean isStriketrough()
    {
        return striketrough;
    }

    public boolean isObfuscated()
    {
        return obfuscated;
    }

    public String getColor()
    {
        return color;
    }
}
