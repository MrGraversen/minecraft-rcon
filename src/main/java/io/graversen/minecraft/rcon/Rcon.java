package io.graversen.minecraft.rcon;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.query.IRconResponseMapper;

import java.util.Objects;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Rcon implements IRcon {
    static final int DEFAULT_TIMEOUT = 5000;

    private final IRconClient rconClient;

    public Rcon(IRconClient rconClient) {
        this.rconClient = Objects.requireNonNull(rconClient);
    }

    @Override
    public RconResponse sendSync(ICommand command) {
        final var responseFuture = sendAsync(command);

        try {
            return responseFuture.get(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RconCommandException(e, "Failed to receive response to command");
        }
    }

    @Override
    public Future<RconResponse> sendAsync(ICommand command) {
        return rconClient.sendRaw(command.command());
    }

    @Override
    public <T> T query(ICommand command, IRconResponseMapper<T> rconResponseMapper) {
        final var rconResponse = sendSync(command);
        return rconResponseMapper.safeApply(rconResponse);
    }
}
