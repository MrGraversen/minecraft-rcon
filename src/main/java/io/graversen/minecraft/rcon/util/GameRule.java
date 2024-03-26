package io.graversen.minecraft.rcon.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum GameRule {
    ANNOUNCE_ADVANCEMENTS,
    COMMAND_BLOCK_OUTPUT,
    DISABLE_ELYTRA_MOVEMENT_CHECK,
    DO_DAYLIGHT_CYCLE,
    DO_ENTITY_DROPS,
    DO_FIRE_TICK,
    DO_LIMITED_CRAFTING,
    DO_MOB_LOOT,
    DO_MOB_SPAWNING,
    DO_TILE_DROPS,
    DO_WEATHER_CYCLE,
    KEEP_INVENTORY,
    LOG_ADMIN_COMMANDS,
    MAX_COMMAND_CHAIN_LENGTH,
    MAX_ENTITY_CRAMMING,
    MOB_GRIEFING,
    NATURAL_GENERATION,
    RANDOM_TICK_SPEED,
    REDUCED_DEBUG_INFO,
    SEND_COMMAND_FEEDBACK,
    SHOW_DEATH_MESSAGES,
    SPAWN_RADIUS,
    SPECTATORS_GENERATE_CHUNKS;

    public String getGameRuleName() {
        String upperCamel = Arrays.stream(name().split("_"))
                .map(part -> part.charAt(0) + part.substring(1).toLowerCase())
                .collect(Collectors.joining());
        return Character.toLowerCase(upperCamel.charAt(0)) + upperCamel.substring(1);
    }
}
