package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseCommand;
import io.graversen.minecraft.rcon.util.GameModes;

public class GameModeCommand extends BaseCommand {
    private final GameModes gameMode;
    private final String playerName;

    public GameModeCommand(GameModes gameMode, String playerName) {
        this.gameMode = gameMode;
        this.playerName = playerName;
    }

    public GameModes getGameMode() {
        return gameMode;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toCommandString() {
        if (playerName == null) {
            return String.format("gamemode %s", gameMode.getGameModeString());
        } else {
            return String.format("gamemode %s %s", gameMode.getGameModeString(), playerName);
        }
    }
}
