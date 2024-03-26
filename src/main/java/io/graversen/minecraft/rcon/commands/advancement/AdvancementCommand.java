package io.graversen.minecraft.rcon.commands.advancement;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;

public class AdvancementCommand extends BaseTargetedCommand {
    private final AdvancementAction action;
    private final AdvancementTarget advancementTarget;

    public AdvancementCommand(Target target, AdvancementAction action, AdvancementTarget advancementTarget) {
        super(target);
        this.action = action;
        this.advancementTarget = advancementTarget;
    }

    @Override
    public String command() {
        return ("advancement " +
                action.getAdvancementActionName() + " "
                + getTarget() + " "
                + advancementTarget.getTargetType().getTargetName() + " "
                + advancementTarget.getAdvancement() + " "
                + (advancementTarget.getCriterion() != null ? advancementTarget.getCriterion() : "")
        ).trim();
    }
}
