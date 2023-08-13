package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

import java.util.Objects;

public class ScheduleCommand implements ICommand {
    public enum ScheduleType {
        APPEND,
        REPLACE,
        DEFAULT;

        String getType() {
            return this == DEFAULT ? "" : name().toLowerCase();
        }
    }

    private final String command;

    private ScheduleCommand(String command) {
        this.command = command;
    }

    public static ScheduleCommand function(String function, double time, ScheduleType scheduleType) {
        var type = Objects.requireNonNull(scheduleType).getType();
        return new ScheduleCommand("function " + Objects.requireNonNull(function) + " " + time + (type.isEmpty() ? type : " " + type));
    }

    public static ScheduleCommand clear(String function) {
        return new ScheduleCommand("clear " + Objects.requireNonNull(function));
    }

    @Override
    public String command() {
        return "schedule " + command;
    }
}
