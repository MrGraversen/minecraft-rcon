package io.graversen.minecraft.rcon;

import io.graversen.minecraft.rcon.commands.SaveAllCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveAllCommandTest {
    @Test
    void saveAll() {
        assertEquals("save-all", new SaveAllCommand(false).command());
    }

    @Test
    void saveAll_flush() {
        assertEquals("save-all flush", new SaveAllCommand(true).command());
    }
}
