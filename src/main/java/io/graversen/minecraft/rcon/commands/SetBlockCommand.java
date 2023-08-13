package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Position;

public class SetBlockCommand implements ICommand {
    public enum Mode {
        DESTROY,
        KEEP,
        REPLACE;

        String mode() {
            return this.name().toLowerCase();
        }
    }

    private final Position position;
    private final String block;
    private final Mode mode;

    public SetBlockCommand(Position position, String block) {
        this(position, block, Mode.REPLACE);
    }

    public SetBlockCommand(Position position, String block, Mode mode) {
        this.position = position;
        this.block = block;
        this.mode = mode;
    }

    @Override
    public String command() {
        return "setblock " + position + " " + block + " " + mode.mode();
    }
}
