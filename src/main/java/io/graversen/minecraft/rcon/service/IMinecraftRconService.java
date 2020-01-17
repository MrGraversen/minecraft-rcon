package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.MinecraftRcon;

import java.util.Optional;

public interface IMinecraftRconService {
    void connect();

    void disconnect();

    boolean isConnected();

    Optional<MinecraftRcon> minecraftRcon();
}
