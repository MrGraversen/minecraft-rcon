package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Position2D;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Arrays;

public class SpreadPlayersCommand implements ICommand {
    private final Position2D center;
    private final double spreadDistance;
    private final double maxRange;
    private final int maxHeight;
    private final boolean respectTeams;
    private final Target[] targets;

    public SpreadPlayersCommand(Position2D center, double spreadDistance, double maxRange, boolean respectTeams, Target... targets) {
        this.center = center;
        this.spreadDistance = spreadDistance;
        this.maxRange = maxRange;
        this.maxHeight = -1;
        this.respectTeams = respectTeams;
        this.targets = targets;
    }

    public SpreadPlayersCommand(Position2D center, double spreadDistance, double maxRange, int maxHeight, boolean respectTeams, Target... targets) {
        this.center = center;
        this.spreadDistance = spreadDistance;
        this.maxRange = maxRange;
        this.maxHeight = maxHeight;
        this.respectTeams = respectTeams;
        this.targets = targets;
    }

    @Override
    public String command() {
        return "spreadplayers " +
                center + " " +
                spreadDistance + " " +
                maxRange + " " +
                (maxHeight > 0 ? "under " + maxHeight + " " : "") +
                respectTeams + " " +
                String.join(" ", Arrays.stream(targets).map(Target::toString).toArray(String[]::new));
    }
}
