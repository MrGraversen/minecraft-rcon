package io.graversen.minecraft.rcon.commands.base;

public interface ICommandBuilder<T> {
    boolean validate();

    T build();
}
