package io.graversen.minecraft.rcon;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.query.IRconResponseMapper;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.UnaryOperator;

public class MinecraftRcon implements IMinecraftRcon {
    private final IMinecraftClient rconClient;
    private static final UnaryOperator<String> commandFailMessage = command -> "Failed to receive response to command \"" + command + "\"";

    public MinecraftRcon(IMinecraftClient rconClient) {
        this.rconClient = Objects.requireNonNull(rconClient);
    }

    private static <T> CompletableFuture<T> futureToCompletable(Future<T> future, RuntimeException onFailureException) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw onFailureException;
            } catch (CancellationException | ExecutionException e) {
                throw onFailureException;
            }
        });
    }

    @Override
    public Future<RconResponse> sendAsync(ICommand command) {
        return rconClient.sendRaw(commandToString(command));
    }

    @Override
    public void sendAsync(Runnable onCompletion, ICommand command) {
        futureToCompletable(
                sendAsync(command),
                new RconCommandException(commandFailMessage.apply(commandToString(command)))
        ).thenRun(onCompletion);
    }

    @Override
    public void sendAsync(ICommand... commands) {
        Arrays.stream(commands).forEach(this::sendAsync);
    }

    @Override
    public void sendAsync(Runnable onCompletion, ICommand... commands) {
        var allCommands = Arrays.stream(commands)
                .map(command -> futureToCompletable(
                        sendAsync(command),
                        new RconCommandException(commandFailMessage.apply(commandToString(command)))
                )).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(allCommands).thenRun(onCompletion);
    }

    @Override
    public RconResponse sendSync(ICommand command) {
        final var responseFuture = sendAsync(command);

        try {
            return responseFuture.get(Defaults.RCON_TIMEOUT.toMillis(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RconCommandException(e, commandFailMessage.apply(commandToString(command)));
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
