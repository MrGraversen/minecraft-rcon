package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Target;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpawnPointCommandTest {
    @Test
    void spawnPoint() {
        assertEquals("spawnpoint test 9 -9 9", new SpawnPointCommand(Target.player("test"), Position.simple(9, -9, 9)).command());
    }
}