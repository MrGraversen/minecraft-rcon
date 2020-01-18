package io.graversen.minecraft.rcon;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.query.IRconResponseMapper;

import java.util.concurrent.Future;

public interface IMinecraftRcon {
    RconResponse sendSync(ICommand command);

    Future<RconResponse> sendAsync(ICommand command);

    void sendAsync(ICommand... commands);

    <T> T query(ICommand command, IRconResponseMapper<T> rconResponseMapper);
}
