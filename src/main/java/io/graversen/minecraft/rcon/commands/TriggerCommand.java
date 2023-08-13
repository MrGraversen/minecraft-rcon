package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

public class TriggerCommand implements ICommand {
    private final String objectiveName;

    public TriggerCommand(String objectiveName) {
        this.objectiveName = objectiveName;
    }

    public static TriggerCommand add(String objectiveName, int value) {
        return new TriggerCommand(objectiveName + " add " + value);
    }

    public static TriggerCommand set(String objectiveName, int value) {
        return new TriggerCommand(objectiveName + " set " + value);
    }

    @Override
    public String command() {
        return "trigger " + objectiveName;
    }
}
