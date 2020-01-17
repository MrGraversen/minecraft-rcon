package io.graversen.minecraft.rcon.service;

import java.time.Duration;

public class ConnectOptions {
    private final int maxRetries;
    private final Duration timeBetweenRetries;

    public ConnectOptions(int maxRetries, Duration timeBetweenRetries) {
        this.maxRetries = maxRetries;
        this.timeBetweenRetries = timeBetweenRetries;
    }

    public static ConnectOptions defaults() {
        return new ConnectOptions(3, Duration.ofSeconds(3));
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public Duration getTimeBetweenRetries() {
        return timeBetweenRetries;
    }
}
