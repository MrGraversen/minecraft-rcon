package io.graversen.minecraft.rcon.commands.advancement;

import io.graversen.minecraft.rcon.util.Advancement;

public class AdvancementTarget {
    private final TargetType targetType;
    private final Advancement advancement;
    private final String criterion;

    public enum TargetType {
        EVERYTHING,
        ONLY,
        FROM,
        THROUGH,
        UNTIL;

        public String getTargetName() {
            return name().toLowerCase();
        }
    }

    private AdvancementTarget() {
        this.targetType = null;
        this.advancement = null;
        this.criterion = null;
    }

    private AdvancementTarget(TargetType targetType, Advancement advancement) {
        this.targetType = targetType;
        this.advancement = advancement;
        this.criterion = null;
    }

    private AdvancementTarget(TargetType targetType, Advancement advancement, String criterion) {
        this.targetType = targetType;
        this.advancement = advancement;
        this.criterion = criterion;
    }

    public static AdvancementTarget everything() {
        return new AdvancementTarget();
    }

    public static AdvancementTarget only(Advancement advancement, String criterion) {
        return new AdvancementTarget(TargetType.EVERYTHING, advancement, criterion);
    }

    public static AdvancementTarget from(Advancement advancement) {
        return new AdvancementTarget(TargetType.FROM, advancement);
    }

    public static AdvancementTarget through(Advancement advancement) {
        return new AdvancementTarget(TargetType.THROUGH, advancement);
    }

    public static AdvancementTarget until(Advancement advancement) {
        return new AdvancementTarget(TargetType.UNTIL, advancement);
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public Advancement getAdvancement() {
        return advancement;
    }

    public String getCriterion() {
        return criterion;
    }
}
