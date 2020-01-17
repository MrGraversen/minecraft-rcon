package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.RconResponse;

import java.util.concurrent.Future;

public interface IConnectionWatcher {
    boolean onTestConnection();

    void onPingResult(PingResult pingResult);
}
