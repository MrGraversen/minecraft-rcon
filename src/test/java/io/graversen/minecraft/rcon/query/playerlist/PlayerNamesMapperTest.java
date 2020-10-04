package io.graversen.minecraft.rcon.query.playerlist;

import io.graversen.minecraft.rcon.RconResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerNamesMapperTest {
    private final PlayerNamesMapper playerNamesMapper = new PlayerNamesMapper();

    @Test
    void apply_playersOnline() {
        final var testRconResponse =
                new RconResponse(0, 0, 0, 0, "There are 2 of a max 20 players online: MrSkurk (ab9b6457-e657-4a9c-ace6-22a291f92035), test (bb9b6457-e657-4a9c-ace6-22a291f92035)");

        final var playerList = playerNamesMapper.apply(testRconResponse);

        assertNotNull(playerList);
        assertEquals(2, playerList.getPlayerNames().size());
        assertEquals("MrSkurk", playerList.getPlayerNames().get(0));
        assertEquals("test", playerList.getPlayerNames().get(1));

    }

    @Test
    void apply_noPlayersOnline() {
        final var testRconResponse =
                new RconResponse(0, 0, 0, 0, "There are 0 of a max 20 players online: ");

        final var playerList = playerNamesMapper.apply(testRconResponse);

        assertNotNull(playerList);
        assertTrue(playerList.getPlayerNames().isEmpty());
    }
}