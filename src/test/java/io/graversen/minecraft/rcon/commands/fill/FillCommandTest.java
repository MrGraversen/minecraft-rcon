package io.graversen.minecraft.rcon.commands.fill;

import io.graversen.minecraft.rcon.util.MinecraftBlock;
import io.graversen.minecraft.rcon.util.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FillCommandTest {
    @Test
    void nullPosition() {
        assertThrows(NullPointerException.class, () -> FillCommandBuilder.bounds(null, null));
    }

    @Test
    void unfinishedThrows() {
        final var unfinishedBuilder = FillCommandBuilder.bounds(Position.simple(1, 2, 3), Position.simple(-1, -2, -3));
        assertThrows(IllegalStateException.class, unfinishedBuilder::build);
    }

    @Test
    void replace_1() {
        final var fillCommand = FillCommandBuilder
                .bounds(Position.simple(1, 2, 3), Position.simple(-1, -2, -3))
                .replace(new MinecraftBlock("gold_block"));

        assertEquals("fill 1 2 3 -1 -2 -3 minecraft:gold_block replace", fillCommand.command());
    }

    @Test
    void replace_2() {
        final var fillCommand = FillCommandBuilder
                .bounds(Position.simple(1, 2, 3), Position.simple(-1, -2, -3))
                .replace(new MinecraftBlock("gold_block"), new MinecraftBlock("diamond_block"));

        assertEquals("fill 1 2 3 -1 -2 -3 minecraft:gold_block replace minecraft:diamond_block", fillCommand.command());
    }

    @Test
    void outline() {
        final var fillCommand = FillCommandBuilder
                .bounds(Position.simple(1, 2, 3), Position.simple(-1, -2, -3))
                .outline(new MinecraftBlock("gold_block"));

        assertEquals("fill 1 2 3 -1 -2 -3 minecraft:gold_block outline", fillCommand.command());
    }

    @Test
    void keep() {
        final var fillCommand = FillCommandBuilder
                .bounds(Position.simple(1, 2, 3), Position.simple(-1, -2, -3))
                .keep(new MinecraftBlock("gold_block"));

        assertEquals("fill 1 2 3 -1 -2 -3 minecraft:gold_block keep", fillCommand.command());
    }

    @Test
    void hollow() {
        final var fillCommand = FillCommandBuilder
                .bounds(Position.simple(1, 2, 3), Position.simple(-1, -2, -3))
                .hollow(new MinecraftBlock("gold_block"));

        assertEquals("fill 1 2 3 -1 -2 -3 minecraft:gold_block hollow", fillCommand.command());
    }

    @Test
    void destroy() {
        final var fillCommand = FillCommandBuilder
                .bounds(Position.simple(1, 2, 3), Position.simple(-1, -2, -3))
                .destroy(new MinecraftBlock("gold_block"));

        assertEquals("fill 1 2 3 -1 -2 -3 minecraft:gold_block destroy", fillCommand.command());
    }
}