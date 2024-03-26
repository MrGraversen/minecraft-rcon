package io.graversen.minecraft.rcon.commands.effect;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;

public class EffectCommand extends BaseTargetedCommand {
    private final String clear;
    private final String effect;
    private final int seconds;
    private final int amplifier;
    private final boolean hideParticles;

    public EffectCommand(Target target, String clear, String effect, int seconds, int amplifier, boolean hideParticles) {
        super(target);
        this.clear = clear;
        this.effect = effect;
        this.seconds = seconds;
        this.amplifier = amplifier;
        this.hideParticles = hideParticles;
    }

    public String getClear() {
        return clear;
    }

    public String getEffect() {
        return effect;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public boolean isHideParticles() {
        return hideParticles;
    }

    @Override
    public String command() {
        if (getClear().isEmpty()) {
            String partialCommand = "effect " + getTarget() + " " + getEffect() + " " + getSeconds();
            if (getAmplifier() > 0) partialCommand += " " + getAmplifier();
            if (isHideParticles()) partialCommand += " " + isHideParticles();

            return partialCommand;
        } else {
            return "effect " + getTarget() + " clear";
        }
    }
}
