package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseCommand;
import io.graversen.minecraft.rcon.util.WhiteListModes;

public class WhiteListCommand extends BaseCommand {
    private final WhiteListModes whiteListMode;
    private final String playerName;

    public WhiteListCommand(WhiteListModes whiteListMode, String playerName) {
        this.whiteListMode = whiteListMode;
        this.playerName = playerName;
    }

    public WhiteListModes getWhiteListMode() {
        return whiteListMode;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toCommandString() {
        switch (getWhiteListMode()) {
            case ADD:
            case REMOVE:
                return String.format("whitelist %s %s", getWhiteListMode().getModeName(), getPlayerName());
            case LIST:
            case OFF:
            case ON:
            case RELOAD:
                return String.format("whitelist %s", getWhiteListMode().getModeName());
            default:
                throw new UnsupportedOperationException("Unsupported whitelist mode: " + getWhiteListMode());
        }
    }
}
