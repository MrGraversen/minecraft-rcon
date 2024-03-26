package io.graversen.minecraft.rcon.query.playerlist;

import io.graversen.minecraft.rcon.RconResponse;
import io.graversen.minecraft.rcon.query.IRconResponseMapper;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PlayerNamesMapper implements IRconResponseMapper<PlayerNames> {
    static final Pattern PATTERN_INSIDE_PARENTHESIS = Pattern.compile("\\(.*\\)");
    static final Pattern PATTERN_INITIAL = Pattern.compile(":");
    static final Pattern PATTERN_PLAYERS = Pattern.compile(",");

    @Override
    public PlayerNames apply(RconResponse rconResponse) {
        if (rconResponse.getResponseString() != null) {
            final String responseString = rconResponse.getResponseString().trim();
            final String[] players = responseString.split(PATTERN_INITIAL.pattern());

            if (players.length == 2) {
                return extractPlayerList(players[1]);
            } else {
                return new PlayerNames(List.of());
            }
        }

        return new PlayerNames(List.of());

    }

    private PlayerNames extractPlayerList(String playersRaw) {
        final var players = Arrays.stream(playersRaw.split(PATTERN_PLAYERS.pattern()))
                .map(playerRaw -> playerRaw.replaceAll(PATTERN_INSIDE_PARENTHESIS.pattern(), ""))
                .map(String::trim)
                .toList();

        return new PlayerNames(players);
    }
}
