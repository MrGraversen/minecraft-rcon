package io.graversen.minecraft.rcon.query.playerlist;

import io.graversen.minecraft.rcon.RconResponse;
import io.graversen.minecraft.rcon.query.IRconResponseMapper;

import java.util.List;

public class PlayerListMapper implements IRconResponseMapper<PlayerList> {
    @Override
    public PlayerList apply(RconResponse rconResponse) {
        final String[] players = rconResponse.getResponseString().split(":");

        if (players.length == 2) {
            return new PlayerList(List.of(players[1].split(",")));
        } else {
            return new PlayerList(List.of());
        }
    }
}
