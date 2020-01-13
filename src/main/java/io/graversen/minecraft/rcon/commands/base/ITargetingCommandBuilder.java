package io.graversen.minecraft.rcon.commands.base;

import io.graversen.minecraft.rcon.util.Selectors;

public interface ITargetingCommandBuilder<T extends ICommandBuilder<?>> {
    T targeting(String playerName);

    T targeting(Selectors usingSelector);
}
