package io.graversen.minecraft.rcon.service;

public interface IConnectionWatcher {
    boolean onTestConnection();

    void onPingResult(PingResult pingResult);
}
