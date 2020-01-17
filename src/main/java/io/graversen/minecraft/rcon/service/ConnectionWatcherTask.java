package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.IMinecraftClient;

import java.time.Duration;
import java.time.LocalDateTime;

class ConnectionWatcherTask implements Runnable {
    private final IConnectionWatcher connectionWatcher;
    private final IMinecraftClient minecraftClient;

    ConnectionWatcherTask(IConnectionWatcher connectionWatcher, IMinecraftClient minecraftClient) {
        this.connectionWatcher = connectionWatcher;
        this.minecraftClient = minecraftClient;
    }

    @Override
    public void run() {
        final LocalDateTime start = LocalDateTime.now();
        final boolean isConnected = minecraftClient.isConnected(Duration.ofSeconds(1));

        if (isConnected) {
            connectionWatcher.onPingResult(new PingResult(Duration.between(start, LocalDateTime.now()), true));
        } else {
            connectionWatcher.onPingResult(new PingResult(Duration.between(start, LocalDateTime.now()), false));
            throw new RuntimeException("Exiting");
        }
    }
}
