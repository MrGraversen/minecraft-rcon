package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Biome;
import io.graversen.minecraft.rcon.util.Structure;

import java.util.Objects;

public class LocateCommand implements ICommand {
    private final String command;

    private LocateCommand(String command) {
        this.command = command;
    }

    public static LocateCommand structure(Structure structure) {
        return new LocateCommand("structure " + Objects.requireNonNull(structure));
    }

    public static LocateCommand biome(Biome biome) {
        return new LocateCommand("biome " + Objects.requireNonNull(biome));
    }

    public static LocateCommand poi(String poi) {
        return new LocateCommand("poi " + Objects.requireNonNull(poi));
    }

    @Override
    public String command() {
        return "locate " + command;
    }
}
