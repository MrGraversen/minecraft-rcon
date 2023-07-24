package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.GameRules;

import java.util.Objects;

public class GameRulesCommands {
    private GameRulesCommands() {}

    public static ICommand setGameRule(GameRules gameRule, int value) {
        return setGameRule(gameRule.getGameRuleName(), String.valueOf(value));
    }

    public static ICommand setGameRule(GameRules gameRule, boolean value) {
        return setGameRule(gameRule.getGameRuleName(), String.valueOf(value));
    }

    public static ICommand setGameRule(String gameRule, int value) {
        return setGameRule(gameRule, String.valueOf(value));
    }

    public static ICommand setGameRule(String gameRule, boolean value) {
        return setGameRule(gameRule, String.valueOf(value));
    }

    private static ICommand setGameRule(String gameRule, String value) {
        Objects.requireNonNull(gameRule);
        Objects.requireNonNull(value);

        return () -> "gamerule " + gameRule + " " + value;
    }

    private static ICommand getGameRule(String gameRule) {
        Objects.requireNonNull(gameRule);

        return () -> "gamerule " + gameRule;
    }
}
