package io.graversen.minecraft.rcon.query.playerlist;

import java.util.List;

public class PlayerUuids {
    private final List<String> playerUuids;

    PlayerUuids(List<String> playerUuids) {
        this.playerUuids = playerUuids;
    }

    public List<String> getPlayerUuids() {
        return playerUuids;
    }
}
