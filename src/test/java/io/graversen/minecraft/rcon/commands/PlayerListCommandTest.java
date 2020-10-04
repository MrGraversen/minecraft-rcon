package io.graversen.minecraft.rcon.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerListCommandTest {
    @Test
    void playerListCommand_uuids() {
        final var command = PlayerListCommand.uuids();
        assertEquals("list uuids", command.command());
    }

    @Test
    void playerListCommand_names() {
        final var command = PlayerListCommand.names();
        assertEquals("list", command.command());
    }
}