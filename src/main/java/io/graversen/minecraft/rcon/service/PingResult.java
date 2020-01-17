package io.graversen.minecraft.rcon.service;

import java.time.Duration;

class PingResult {
    private final Duration latency;
    private final boolean success;

    public PingResult(Duration latency, boolean success) {
        this.latency = latency;
        this.success = success;
    }

    public Duration getLatency() {
        return latency;
    }

    public boolean isSuccess() {
        return success;
    }
}
