package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Experience;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class ExperienceCommand extends BaseTargetedCommand {
    private final String experienceMode;
    private final Experience experience;

    private ExperienceCommand(Target target, String experienceMode, Experience experience) {
        super(target);
        this.experienceMode = Objects.requireNonNull(experienceMode);
        this.experience = experience;
    }

    public static ExperienceCommand add(Target target, Experience experience) {
        return new ExperienceCommand(target, "add", experience);
    }

    public static ExperienceCommand set(Target target, Experience experience) {
        return new ExperienceCommand(target, "set", experience);
    }

    public static ExperienceCommand queryLevels(Target target) {
        return new ExperienceCommand(target, "query", null);
    }

    public String getExperienceMode() {
        return experienceMode;
    }

    public Experience getExperience() {
        return experience;
    }

    @Override
    public String command() {
        return "experience " +
                getExperienceMode() + " " +
                getTarget() + " " +
                Objects.requireNonNullElse(getExperience(), "levels");
    }
}
