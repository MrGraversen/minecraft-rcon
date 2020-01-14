package io.graversen.minecraft.rcon.commands.effect;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

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
    public String command() {
        if (getClear().isEmpty()) {
            String partialCommand = "effect ${target} ${effect} ${seconds}";
            if (getAmplifier() > 0) partialCommand = partialCommand + " ${amplifier}";
            if (isHideParticles()) partialCommand = partialCommand + " ${hideParticles}";

            final var variables = Map.of(
                    "target", getTarget(),
                    "effect", getEffect(),
                    "seconds", String.valueOf(getSeconds()),
                    "amplifier", String.valueOf(getAmplifier()),
                    "hideParticles", String.valueOf(isHideParticles())
            );
            return StringSubstitutor.replace(partialCommand, variables);
        } else {
            return StringSubstitutor.replace("effect ${target} clear", Map.of("target", getTarget()));
        }
    }
}
