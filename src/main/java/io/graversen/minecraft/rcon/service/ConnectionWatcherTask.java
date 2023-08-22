package io.graversen.minecraft.rcon.service;

import java.time.Duration;
import java.time.LocalDateTime;

class ConnectionWatcherTask implements Runnable {
    private final IConnectionWatcher connectionWatcher;

    ConnectionWatcherTask(IConnectionWatcher connectionWatcher) {
        this.connectionWatcher = connectionWatcher;
    }

    @Override
    public void run() {
        final LocalDateTime start = LocalDateTime.now();
        final boolean isConnected = connectionWatcher.onTestConnection();

        connectionWatcher.onPingResult(pingResult(start, isConnected));
    }

    private PingResult pingResult(LocalDateTime start, boolean success) {
        return new PingResult(Duration.between(start, LocalDateTime.now()), success);
    }
}
