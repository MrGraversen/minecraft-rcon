package io.graversen.minecraft.rcon.query.playerlist;

import java.util.List;

public class PlayerNames {
    private final List<String> playerNames;

    PlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }
}
