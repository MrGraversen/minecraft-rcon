package io.graversen.minecraft.rcon.query.playerlist;

import io.graversen.minecraft.rcon.RconResponse;
import io.graversen.minecraft.rcon.query.IRconResponseMapper;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class PlayerUuidsMapper implements IRconResponseMapper<PlayerUuids> {
    static final Pattern PATTERN_INITIAL = Pattern.compile(":");
    static final Pattern PATTERN_PLAYERS = Pattern.compile(",");

    @Override
    public PlayerUuids apply(RconResponse rconResponse) {
        if (rconResponse.getResponseString() != null) {
            final String responseString = rconResponse.getResponseString().trim();
            final String[] players = responseString.split(PATTERN_INITIAL.pattern());

            if (players.length == 2) {
                return extractPlayerList(players[1]);
            } else {
                return new PlayerUuids(List.of());
            }
        }

        return new PlayerUuids(List.of());
    }

    private PlayerUuids extractPlayerList(String playersRaw) {
        final var players = Arrays.stream(playersRaw.split(PATTERN_PLAYERS.pattern()))
                .map(String::trim)
                .map(player -> player.contains("(") && player.contains(")") ? player.split("\\(")[1].split("\\)")[0] : "")
                .toList();

        return new PlayerUuids(players);
    }
}
