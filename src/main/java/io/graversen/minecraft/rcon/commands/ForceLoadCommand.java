package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Position2D;

import java.util.Arrays;

public class ForceLoadCommand implements ICommand {

    private final ForceLoadAction forceLoadAction;
    private final Position2D[] positions;

    enum ForceLoadAction {
        ADD,
        REMOVE,
        REMOVE_ALL,
        QUERY;

        public String getForceLoadActionName() {
            return name().toLowerCase().replace("_", " ");
        }
    }

    private ForceLoadCommand(ForceLoadAction forceLoadAction, Position2D[] positions) {
        this.forceLoadAction = forceLoadAction;
        this.positions = positions;
    }

    public static ForceLoadCommand add(Position2D from, Position2D to) {
        return new ForceLoadCommand(ForceLoadAction.ADD, new Position2D[]{from, to});
    }

    public static ForceLoadCommand remove(Position2D from, Position2D to) {
        return new ForceLoadCommand(ForceLoadAction.REMOVE, new Position2D[]{from, to});
    }

    public static ForceLoadCommand removeAll() {
        return new ForceLoadCommand(ForceLoadAction.REMOVE_ALL, new Position2D[]{});
    }

    public static ForceLoadCommand query(Position2D position) {
        return new ForceLoadCommand(ForceLoadAction.QUERY, new Position2D[]{position});
    }

    @Override
    public String command() {
        return "forceload "
                + (forceLoadAction.getForceLoadActionName() + " "
                + String.join(" ", Arrays.stream(positions)
                                            .map(Position2D::toString)
                                            .toArray(String[]::new))).trim();
    }
}
