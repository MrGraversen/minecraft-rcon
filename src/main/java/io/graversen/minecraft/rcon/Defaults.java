package io.graversen.minecraft.rcon;

import java.time.Duration;

public final class Defaults {
    public static final String MINECRAFT = "minecraft";
    public static final Duration RCON_TIMEOUT = Duration.ofSeconds(1);
    public static final Duration CONNECTION_WATCHER_INTERVAL = Duration.ofSeconds(1);
    public static final int RCON_PORT = 25575;
    public static final int WORLD_BOUND_MAX = 30_000_000;
    public static final int WORLD_BOUND_MIN = -30_000_000;

    private Defaults() {

    }
}
