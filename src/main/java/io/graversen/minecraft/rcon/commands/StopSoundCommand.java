package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.NumberUtils;
import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Sound;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class StopSoundCommand extends BaseTargetedCommand {
    private final Sound sound;

    StopSoundCommand(Target target, Sound sound) {
        super(target);
        this.sound = sound;
    }

    public Sound getSound() {
        return sound;
    }

    @Override
    public String command() {
        return ("stopsound " +
                    getTarget() + " " +
                    "master " +
                    (getSound() != null ? getSound() : "")).trim();
    }
}
