package io.graversen.minecraft.rcon.commands.objects;

public class SayCommand
{
    private final String text;

    public SayCommand(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }
}
