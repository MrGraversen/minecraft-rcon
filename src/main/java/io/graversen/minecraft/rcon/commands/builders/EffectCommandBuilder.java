package io.graversen.minecraft.rcon.commands.builders;

import io.graversen.minecraft.rcon.commands.builders.interfaces.ICommandBuilder;
import io.graversen.minecraft.rcon.commands.builders.interfaces.ITargetingCommandBuilder;
import io.graversen.minecraft.rcon.commands.objects.EffectCommand;
import io.graversen.minecraft.rcon.util.Effects;
import io.graversen.minecraft.rcon.util.Selectors;

public class EffectCommandBuilder implements ITargetingCommandBuilder<EffectCommandBuilder>, ICommandBuilder<EffectCommand>
{
    private String target;
    private String clear = "";
    private String effect;
    private int seconds = 30;
    private int amplifier = 1;
    private boolean hideParticles;

    public EffectCommand clearAllEffects(Selectors usingSelector)
    {
        this.clear = "clear";
        this.targeting(usingSelector);
        return build();
    }

    public EffectCommand clearAllEffects(String playerName)
    {
        this.clear = "clear";
        this.targeting(playerName);
        return build();
    }

    @Override
    public EffectCommandBuilder targeting(String playerName)
    {
        this.target = playerName;
        return this;
    }

    @Override
    public EffectCommandBuilder targeting(Selectors usingSelector)
    {
        this.target = usingSelector.getSelectorString();
        return this;
    }

    public EffectCommandBuilder withEffect(Effects effect, int amplifier)
    {
        this.effect = effect.getEffectName();
        this.amplifier = amplifier;
        return this;
    }

    public EffectCommandBuilder withDuration(int seconds)
    {
        this.seconds = seconds;
        return this;
    }

    public EffectCommandBuilder withAmplifier(int amplifier)
    {
        this.amplifier = amplifier;
        return this;
    }

    public EffectCommandBuilder hideParticles()
    {
        this.hideParticles = true;
        return this;
    }

    @Override
    public boolean validate()
    {
        return target != null && (!clear.isEmpty() || effect != null);
    }

    @Override
    public EffectCommand build()
    {
        if (validate())
        {
            return new EffectCommand(target, clear, effect, seconds, amplifier, hideParticles);
        }
        else
        {
            throw new IllegalArgumentException("Could not construct valid Effect Command");
        }
    }
}
