package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class EnchantCommand extends BaseTargetedCommand {
    private final String enchantment;
    private final int level;

    public EnchantCommand(Target target, String enchantment) {
        super(target);
        this.enchantment = Objects.requireNonNull(enchantment);
        this.level = 1;
    }

    public EnchantCommand(Target target, String enchantment, int level) {
        super(target);
        this.enchantment = Objects.requireNonNull(enchantment);
        this.level = level;
    }

    @Override
    public String command() {
        return "enchant " + getTarget() + " " + enchantment + " " + level;
    }
}
