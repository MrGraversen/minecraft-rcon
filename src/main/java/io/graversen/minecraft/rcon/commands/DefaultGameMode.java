package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.GameMode;

import java.util.Objects;

public class DefaultGameMode implements ICommand {
    private final GameMode gameMode;

    public DefaultGameMode(GameMode gameMode) {
        this.gameMode = Objects.requireNonNull(gameMode);
    }

    @Override
    public String command() {
        return "defaultgamemode " + gameMode.getGameModeString();
    }
}
