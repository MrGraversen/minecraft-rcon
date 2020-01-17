package io.graversen.minecraft.rcon;

import java.io.Closeable;
import java.time.Duration;
import java.util.concurrent.Future;

public interface IMinecraftClient extends Closeable {
    boolean isConnected(Duration timeout);

    Future<RconResponse> sendRaw(String command);

    Future<RconResponse> sendRawSilently(String command);
}
