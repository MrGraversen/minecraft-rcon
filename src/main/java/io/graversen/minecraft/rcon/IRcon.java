package io.graversen.minecraft.rcon;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.query.IRconResponseMapper;

import java.util.concurrent.Future;

public interface IRcon {
    RconResponse sendSync(ICommand command);

    Future<RconResponse> sendAsync(ICommand command);

    <T> T query(ICommand command, IRconResponseMapper<T> rconResponseMapper);
}
