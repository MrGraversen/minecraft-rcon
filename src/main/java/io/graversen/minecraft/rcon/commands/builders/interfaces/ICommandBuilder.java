package io.graversen.minecraft.rcon.commands.builders.interfaces;

public interface ICommandBuilder<T>
{
    boolean validate();

    T build();
}
