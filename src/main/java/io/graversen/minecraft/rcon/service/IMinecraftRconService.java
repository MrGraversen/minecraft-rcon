package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.MinecraftRcon;

import java.time.Duration;
import java.util.Optional;

public interface IMinecraftRconService {
    boolean connectBlocking(Duration timeout);

    void connect();

    void disconnect();

    boolean isConnected();

    Optional<MinecraftRcon> minecraftRcon();
}
