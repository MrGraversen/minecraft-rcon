package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.NumberUtils;
import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Sound;
import io.graversen.minecraft.rcon.util.Target;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;
import java.util.Objects;

public class PlaySoundCommand extends BaseTargetedCommand {
    public static final float VOLUME_MIN = 0.0f;
    public static final float VOLUME_MAX = 1.0f;
    public static final float PITCH_MIN = 0.0f;
    public static final float PITCH_MAX = 2.0f;

    private final Sound sound;
    private final Position position;
    private final float volume;
    private final float pitch;

    PlaySoundCommand(Target target, Sound sound, Position position, float volume, float pitch) {
        super(target);
        this.sound = Objects.requireNonNull(sound);
        this.position = Objects.requireNonNull(position);
        this.volume = NumberUtils.enforceBound(volume, VOLUME_MIN, VOLUME_MAX);
        this.pitch = NumberUtils.enforceBound(pitch, PITCH_MIN, PITCH_MAX);
    }

    public static PlaySoundCommand relativeTo(Target target, Sound sound) {
        return relativeTo(target, sound, 1.0f, 1.0f);
    }

    public static PlaySoundCommand relativeTo(Target target, Sound sound, float volume, float pitch) {
        return new PlaySoundCommand(
                target, sound, Position.relative(), volume, pitch
        );
    }

    public Sound getSound() {
        return sound;
    }

    public Position getPosition() {
        return position;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public String command() {
        final var variables = Map.of(
                "sound", getSound(),
                "target", getTarget(),
                "position", getPosition(),
                "volume", String.valueOf(getVolume()),
                "pitch", String.valueOf(getPitch()),
                "minimumVolume", String.valueOf(getVolume())
        );

        final var command = StringSubstitutor.replace(
                "playsound ${sound} master ${target} ${position} ${volume} ${pitch} ${minimumVolume}",
                variables
        );

        return command.trim();
    }
}
