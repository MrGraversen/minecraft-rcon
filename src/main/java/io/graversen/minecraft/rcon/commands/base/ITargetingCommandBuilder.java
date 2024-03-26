package io.graversen.minecraft.rcon.commands.base;

import io.graversen.minecraft.rcon.util.Selector;

public interface ITargetingCommandBuilder<T extends ICommandBuilder<?>> {
    T targeting(String playerName);

    T targeting(Selector usingSelector);
}
