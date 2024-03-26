package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.TimeLabel;

public class TimeCommand implements ICommand {
    private final int time;

    public TimeCommand(int time) {
        this.time = time;
    }

    public TimeCommand(TimeLabel timeLabel){
        this(timeLabel.timeValue());
    }

    public int getTime() {
        return time;
    }

    @Override
    public String command() {
        return "time set " + getTime();
    }
}
