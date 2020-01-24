package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Target;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeleportCommandTest {
    @Test
    void teleportCommand_target() {
        assertEquals("tp player1 player2", new TeleportCommand(Target.player("player1"), Target.player("player2")).command());
    }

    void teleportCommand_position() {
        assertEquals("tp test 3 2 -1", new TeleportCommand(Target.player("test"), Position.simple(3, 2, -1)).command());
    }
}