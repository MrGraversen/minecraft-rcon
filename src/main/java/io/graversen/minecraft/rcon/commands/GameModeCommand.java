package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.GameModes;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;
import java.util.Objects;

public class GameModeCommand extends BaseTargetedCommand {
    private final GameModes gameMode;

    public GameModeCommand(String target, GameModes gameMode) {
        super(target);
        this.gameMode = Objects.requireNonNull(gameMode);
    }

    public GameModes getGameMode() {
        return gameMode;
    }

    @Override
    public String command() {
        return StringSubstitutor.replace(
                "gamemode ${gamemode} ${target}",
                Map.of(
                        "gamemode", getGameMode(),
                        "target", getTarget()
                )
        );
    }
}
