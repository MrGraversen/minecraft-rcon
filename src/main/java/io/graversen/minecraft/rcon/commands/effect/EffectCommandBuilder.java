package io.graversen.minecraft.rcon.commands.effect;

import io.graversen.minecraft.rcon.commands.base.ICommandBuilder;
import io.graversen.minecraft.rcon.commands.base.ITargetingCommandBuilder;
import io.graversen.minecraft.rcon.util.Effects;
import io.graversen.minecraft.rcon.util.Selectors;
import io.graversen.minecraft.rcon.util.Target;

public class EffectCommandBuilder implements ITargetingCommandBuilder<EffectCommandBuilder>, ICommandBuilder<EffectCommand> {
    private Target target;
    private String clear = "";
    private String effect;
    private int seconds = 30;
    private int amplifier = 1;
    private boolean hideParticles;

    public EffectCommand clearAllEffects(Selectors usingSelector) {
        this.clear = "clear";
        this.targeting(usingSelector);
        return build();
    }

    public EffectCommand clearAllEffects(String playerName) {
        this.clear = "clear";
        this.targeting(playerName);
        return build();
    }

    @Override
    public EffectCommandBuilder targeting(String playerName) {
        this.target = Target.player(playerName);
        return this;
    }

    @Override
    public EffectCommandBuilder targeting(Selectors usingSelector) {
        this.target = Target.selector(usingSelector);
        return this;
    }

    public EffectCommandBuilder withEffect(Effects effect, int amplifier) {
        this.effect = effect.getEffectName();
        this.amplifier = amplifier;
        return this;
    }

    public EffectCommandBuilder withDuration(int seconds) {
        this.seconds = seconds;
        return this;
    }

    public EffectCommandBuilder withAmplifier(int amplifier) {
        this.amplifier = amplifier;
        return this;
    }

    public EffectCommandBuilder hideParticles() {
        this.hideParticles = true;
        return this;
    }

    @Override
    public boolean validate() {
        return target != null && (!clear.isEmpty() || effect != null);
    }

    @Override
    public EffectCommand build() {
        if (validate()) {
            return new EffectCommand(target, clear, effect, seconds, amplifier, hideParticles);
        } else {
            throw new IllegalArgumentException("Could not construct valid Effect Command");
        }
    }
}
