package io.graversen.minecraft.rcon.query.playerlist;

import java.util.List;

public class PlayerList {
    private final List<String> playerNames;

    PlayerList(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }
}
