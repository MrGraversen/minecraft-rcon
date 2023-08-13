package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class DamageCommand extends BaseTargetedCommand {
    private final float amount;
    private final String damageType;
    private final Position atLocation;
    private final Target byEntity;
    private final Target fromCause;

    public DamageCommand(Target target, float amount) {
        super(target);
        this.amount = amount;
        this.damageType = null;
        this.atLocation = null;
        this.byEntity = null;
        this.fromCause = null;
    }

    public DamageCommand(Target target, float amount, String damageType) {
        super(target);
        this.amount = amount;
        this.damageType = Objects.requireNonNull(damageType);
        this.atLocation = null;
        this.byEntity = null;
        this.fromCause = null;
    }

    public DamageCommand(Target target, float amount, String damageType, Position atLocation) {
        super(target);
        this.amount = amount;
        this.damageType = Objects.requireNonNull(damageType);
        this.atLocation = Objects.requireNonNull(atLocation);
        this.byEntity = null;
        this.fromCause = null;
    }

    public DamageCommand(Target target, float amount, String damageType, Target byEntity, Target fromCause) {
        super(target);
        this.amount = amount;
        this.damageType = Objects.requireNonNull(damageType);
        this.atLocation = null;
        this.byEntity = Objects.requireNonNull(byEntity);
        this.fromCause = Objects.requireNonNull(fromCause);
    }

    @Override
    public String command() {
        return ("damage " + getTarget()
                + " " + amount
                + (damageType != null ? " " + damageType : "")
                + (damageType != null && atLocation != null ? " " + atLocation : "")
                + (damageType != null && fromCause != null && byEntity != null
                    ? " " + byEntity + " " + fromCause
                    : "")
                ).trim();
    }
}
