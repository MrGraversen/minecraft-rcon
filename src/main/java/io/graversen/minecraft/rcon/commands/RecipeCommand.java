package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Target;

public class RecipeCommand implements ICommand {
    public enum RecipeAction {
        GIVE,
        TAKE;

        String getAction() {
            return this.name().toLowerCase();
        }
    }

    private final String command;

    public RecipeCommand(RecipeAction action, Target player, String recipe) {
        command = action.getAction() + " " + player + " " + (recipe == null ? "*" : recipe);
    }

    @Override
    public String command() {
        return "recipe " + command;
    }
}
