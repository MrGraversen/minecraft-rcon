package io.graversen.minecraft.rcon;

import java.io.Closeable;
import java.util.concurrent.Future;

public interface IMinecraftClient extends Closeable {
    Future<RconResponse> sendRaw(String command);
}
