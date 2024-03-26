package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.Defaults;

import java.time.Duration;

public record ConnectOptions(int maxRetries, Duration timeBetweenRetries, Duration connectionWatcherInterval) {

    public static ConnectOptions defaults() {
        return new ConnectOptions(3, Duration.ofSeconds(3), Defaults.CONNECTION_WATCHER_INTERVAL);
    }

    public static ConnectOptions neverStopTrying() {
        return new ConnectOptions(Integer.MAX_VALUE, Duration.ofSeconds(3), Defaults.CONNECTION_WATCHER_INTERVAL);
    }

    @Override
    public String toString() {
        return "ConnectOptions{" +
                "maxRetries=" + maxRetries +
                ", timeBetweenRetries=" + timeBetweenRetries +
                ", connectionWatcherInterval=" + connectionWatcherInterval +
                '}';
    }
}
