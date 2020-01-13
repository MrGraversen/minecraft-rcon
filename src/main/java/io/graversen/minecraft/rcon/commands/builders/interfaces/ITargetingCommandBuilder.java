package io.graversen.minecraft.rcon.commands.builders.interfaces;

import io.graversen.minecraft.rcon.util.Selectors;

public interface ITargetingCommandBuilder<T extends ICommandBuilder<?>> {
    T targeting(String playerName);

    T targeting(Selectors usingSelector);
}
