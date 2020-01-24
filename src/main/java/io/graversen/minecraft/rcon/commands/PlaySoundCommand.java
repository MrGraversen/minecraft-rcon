package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;
import java.util.Objects;

public class PlaySoundCommand extends BaseTargetedCommand {
    private final String sound;

    public PlaySoundCommand(Target target, String sound) {
        super(target);
        this.sound = Objects.requireNonNull(sound);
    }

    public String getSound() {
        return sound;
    }

    @Override
    public String command() {
        return StringSubstitutor.replace(
                "playsound ${sound} master ${target}",
                Map.of(
                        "sound", getSound(),
                        "target", getTarget()
                )
        );
    }
}
