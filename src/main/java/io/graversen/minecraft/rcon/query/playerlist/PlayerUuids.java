package io.graversen.minecraft.rcon.query.playerlist;

import java.util.List;

public class PlayerUuids {
    private final List<String> uuids;

    PlayerUuids(List<String> playerUuids) {
        this.uuids = playerUuids;
    }

    public List<String> getPlayerUuids() {
        return uuids;
    }
}
