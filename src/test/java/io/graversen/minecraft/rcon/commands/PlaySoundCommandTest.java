package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.util.MinecraftSound;
import io.graversen.minecraft.rcon.util.Target;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaySoundCommandTest {
    @Test
    void relativeTo_targetPlayer_experienceOrbSound() {
        final var command = PlaySoundCommand.relativeTo(Target.player("test"), new MinecraftSound("entity.experience_orb.pickup"));
        assertEquals("playsound minecraft:entity.experience_orb.pickup master test ~0 ~0 ~0 1.0 1.0 1.0", command.command());
    }

    @Test
    void relativeTo_targetPlayer_boundsEnforced() {
        final var command = PlaySoundCommand.relativeTo(
                Target.player("test"),
                new MinecraftSound("entity.experience_orb.pickup"),
                PlaySoundCommand.VOLUME_MAX * 100f,
                PlaySoundCommand.PITCH_MIN - (PlaySoundCommand.PITCH_MIN + 100f)
        );

        assertEquals(PlaySoundCommand.VOLUME_MAX, command.getVolume());
        assertEquals(PlaySoundCommand.PITCH_MIN, command.getPitch());
        assertEquals("playsound minecraft:entity.experience_orb.pickup master test ~0 ~0 ~0 1.0 0.0 1.0", command.command());
    }
}