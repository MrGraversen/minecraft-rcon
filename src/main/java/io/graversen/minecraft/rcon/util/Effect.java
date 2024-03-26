package io.graversen.minecraft.rcon.util;

public enum Effect {
    ABSORPTION,
    UNLUCK,
    BLINDNESS,
    CONDUIT_POWER,
    FIRE_RESISTANCE,
    GLOWING,
    HASTE,
    HUNGER,
    INSTANT_DAMAGE,
    INSTANT_HEALTH,
    INVISIBILITY,
    JUMP_BOOST,
    LUCK,
    MINING_FATIGUE,
    NAUSEA,
    NIGHT_VISION,
    POISON,
    REGENERATION,
    RESISTANCE,
    SLOW_FALLING,
    SPEED,
    WATER_BREATHING,
    WITHER;

    public String getEffectName() {
        return String.format("minecraft:%s", name().toLowerCase());
    }
}
