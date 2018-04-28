package io.graversen.minecraft.rcon.commands.objects;

public class DifficultyCommand
{
    private final String difficulty;

    public DifficultyCommand(String difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getDifficulty()
    {
        return difficulty;
    }
}
