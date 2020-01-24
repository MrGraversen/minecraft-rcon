package io.graversen.minecraft.rcon.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReloadCommandTest {
    @Test
    void command() {
        assertEquals("reload", new ReloadCommand().command());
    }
}