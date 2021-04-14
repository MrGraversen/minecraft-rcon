package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.MinecraftClient;
import io.graversen.minecraft.rcon.RconConnectException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
class ConnectTask implements Callable<MinecraftClient> {
    private final ConnectOptions connectOptions;
    private final RconDetails rconDetails;

    ConnectTask(ConnectOptions connectOptions, RconDetails rconDetails) {
        this.connectOptions = connectOptions;
        this.rconDetails = rconDetails;
        log.debug("{}", connectOptions);
    }

    @Override
    public MinecraftClient call() throws Exception {
        int currentAttempt = 0;

        while (currentAttempt < connectOptions.getMaxRetries() && !Thread.currentThread().isInterrupted()) {
            currentAttempt++;
            log.debug("Connection attempt {}", currentAttempt);

            try {
                return MinecraftClient.connect(rconDetails.getHostname(), rconDetails.getPassword(), rconDetails.getPort());
            } catch (Exception e) {
                log.error("Connection attempt failed", e);
            } finally {
                if (currentAttempt < connectOptions.getMaxRetries()) {
                    sleep();
                } else {
                    log.warn("Ran out of retries after {} total attempts", currentAttempt);
                }
            }
        }

        throw new RconConnectException("Unable to connect to Minecraft server after %d retries", currentAttempt - 1);
    }

    private void sleep() {
        try {
            log.debug("Pausing for {} ms", connectOptions.getTimeBetweenRetries().toMillis());
            Thread.sleep(connectOptions.getTimeBetweenRetries().toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
