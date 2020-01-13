package io.graversen.minecraft.rcon.commands.effect;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;

public class EffectCommand extends BaseTargetedCommand {
    private final String clear;
    private final String effect;
    private final int seconds;
    private final int amplifier;
    private final boolean hideParticles;

    public EffectCommand(String target, String clear, String effect, int seconds, int amplifier, boolean hideParticles) {
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
    public String toCommandString() {
        if (getClear().isEmpty()) {
            String partialCommand = String.format("effect %s %s %d", getTarget(), getEffect(), getSeconds());
            if (getAmplifier() > 0) partialCommand = partialCommand + String.format(" %d", getAmplifier());
            if (isHideParticles()) partialCommand = partialCommand + " true";

            return partialCommand;
        } else {
            return String.format("effect %s clear", getTarget());
        }
    }
}
