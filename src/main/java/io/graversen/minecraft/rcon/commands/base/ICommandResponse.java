package io.graversen.minecraft.rcon.commands.base;

import io.graversen.minecraft.rcon.RconResponse;

@FunctionalInterface
public interface ICommandResponse {
    void onCommandResponse(RconResponse rconResponse);
}
