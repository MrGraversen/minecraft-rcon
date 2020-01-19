package io.graversen.minecraft.rcon;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.query.IRconResponseMapper;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MinecraftRcon implements IMinecraftRcon {
    private final IMinecraftClient rconClient;

    public MinecraftRcon(IMinecraftClient rconClient) {
        this.rconClient = Objects.requireNonNull(rconClient);
    }

    @Override
    public Future<RconResponse> sendAsync(ICommand command) {
        return rconClient.sendRaw(commandToString(command));
    }

    @Override
    public void sendAsync(ICommand... commands) {
        Arrays.stream(commands).forEach(this::sendAsync);
    }

    @Override
    public RconResponse sendSync(ICommand command) {
        final var responseFuture = sendAsync(command);

        try {
            return responseFuture.get(Defaults.RCON_TIMEOUT.toMillis(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RconCommandException(e, "Failed to receive response to command");
        }
    }

    @Override
    public <T> T query(ICommand command, IRconResponseMapper<T> rconResponseMapper) {
        final var rconResponse = sendSync(command);
        return rconResponseMapper.safeApply(rconResponse);
    }

    private String commandToString(ICommand command) {
        return command.command().trim();
    }
}
