package io.graversen.minecraft.rcon.query.playerlist;

import java.util.List;

public class PlayerNames {
    private final List<String> names;

    PlayerNames(List<String> playerNames) {
        this.names = playerNames;
    }

    public List<String> getPlayerNames() {
        return names;
    }
}
