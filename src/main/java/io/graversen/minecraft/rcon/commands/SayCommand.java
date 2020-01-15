package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class SayCommand implements ICommand {
    private final String text;

    public SayCommand(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String command() {
        return "say " + getText();
    }
}
