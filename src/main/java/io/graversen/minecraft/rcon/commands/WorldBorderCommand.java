package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Position2D;

public class WorldBorderCommand implements ICommand {
    private final String command;

    private WorldBorderCommand(String command) {
        this.command = command.trim();
    }

    public static WorldBorderCommand add(double distance) {
        return add(distance, 0);
    }

    public static WorldBorderCommand add(double distance, int timeInSeconds) {
        return new WorldBorderCommand("worldborder add " + distance + " " + timeInSeconds);
    }

    public static WorldBorderCommand center(Position2D position) {
        return new WorldBorderCommand("worldborder center " + position);
    }

    public static WorldBorderCommand damageAmount(double damagePerBlock) {
        return new WorldBorderCommand("worldborder damage amount " + damagePerBlock);
    }

    public static WorldBorderCommand damageBuffer(float distance) {
        return new WorldBorderCommand("worldborder damage buffer " + distance);
    }

    public static WorldBorderCommand get() {
        return new WorldBorderCommand("worldborder get");
    }

    public static WorldBorderCommand set(double distance) {
        return set(distance, 0);
    }

    public static WorldBorderCommand set(double distance, int timeInSeconds) {
        return new WorldBorderCommand("worldborder set " + distance + " " + timeInSeconds);
    }

    public static WorldBorderCommand warningDistance(int warningDistance) {
        return new WorldBorderCommand("worldborder warning distance " + warningDistance);
    }

    public static WorldBorderCommand warningTime(int warningTime) {
        return new WorldBorderCommand("worldborder warning time " + warningTime);
    }

    @Override
    public String command() {
        return "worldborder " + command;
    }
}
