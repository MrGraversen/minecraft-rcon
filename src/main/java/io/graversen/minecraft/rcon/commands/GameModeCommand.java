package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.GameMode;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class GameModeCommand extends BaseTargetedCommand {
    private final GameMode gameMode;

    public GameModeCommand(Target target, GameMode gameMode) {
        super(target);
        this.gameMode = Objects.requireNonNull(gameMode);
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public String command() {
        return "gamemode " + getGameMode() + " " + getTarget();
    }
}
