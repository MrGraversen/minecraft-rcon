package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.util.Experience;
import io.graversen.minecraft.rcon.util.Selectors;
import io.graversen.minecraft.rcon.util.Target;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExperienceCommandTest {
    @Test
    void add() {
        final var experienceCommand = ExperienceCommand.add(Target.selector(Selectors.ALL_PLAYERS), Experience.levels(10));
        assertEquals("experience add @a 10 levels", experienceCommand.command());
    }

    @Test
    void set() {
        final var experienceCommand = ExperienceCommand.set(Target.player("test"), Experience.points(999));
        assertEquals("experience set test 999 points", experienceCommand.command());
    }

    @Test
    void queryLevels() {
        final var experienceCommand = ExperienceCommand.queryLevels(Target.player("test"));
        assertEquals("experience query test levels", experienceCommand.command());
    }
}