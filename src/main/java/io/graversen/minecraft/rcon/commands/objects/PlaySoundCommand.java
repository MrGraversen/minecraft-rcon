package io.graversen.minecraft.rcon.commands.objects;

import io.graversen.minecraft.rcon.commands.objects.base.BaseTargetedCommand;

public class PlaySoundCommand extends BaseTargetedCommand {
    private final String sound;

    public PlaySoundCommand(String target, String sound) {
        super(target);
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }
}
