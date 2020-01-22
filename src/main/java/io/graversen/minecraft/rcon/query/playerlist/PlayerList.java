package io.graversen.minecraft.rcon.query.playerlist;

import java.util.List;

public class PlayerList {
    private final List<String> playerUuids;

    PlayerList(List<String> playerUuids) {
        this.playerUuids = playerUuids;
    }

    public List<String> getPlayerUuids() {
        return playerUuids;
    }
}
