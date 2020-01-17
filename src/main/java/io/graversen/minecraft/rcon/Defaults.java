package io.graversen.minecraft.rcon;

import java.time.Duration;

public final class Defaults {
    public static final Duration CONNECTION_WATCHER_INTERVAL = Duration.ofSeconds(1);
    public static final int DEFAULT_RCON_PORT = 25575;

    private Defaults() {

    }
}
