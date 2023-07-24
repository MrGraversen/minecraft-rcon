package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.GameModes;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class GameModeCommand extends BaseTargetedCommand {
    private final GameModes gameMode;

    public GameModeCommand(Target target, GameModes gameMode) {
        super(target);
        this.gameMode = Objects.requireNonNull(gameMode);
    }

    public GameModes getGameMode() {
        return gameMode;
    }

    @Override
    public String command() {
        return "gamemode " + getGameMode() + " " + getTarget();
    }
}
