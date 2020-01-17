package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.IMinecraftClient;

import java.util.Optional;

public interface IMinecraftClientService {
    void connect();

    void disconnect();

    boolean isConnected();

    Optional<IMinecraftClient> minecraftClient();
}
