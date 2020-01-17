package io.graversen.minecraft.rcon.service;

@FunctionalInterface
public interface IConnectionWatcher {
    void onPingResult(PingResult pingResult);
}
