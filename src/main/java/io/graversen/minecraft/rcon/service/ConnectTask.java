package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.MinecraftClient;
import io.graversen.minecraft.rcon.RconConnectException;
import org.tinylog.Logger;

import java.util.concurrent.Callable;

class ConnectTask implements Callable<MinecraftClient> {
    private final ConnectOptions connectOptions;
    private final RconDetails rconDetails;

    ConnectTask(ConnectOptions connectOptions, RconDetails rconDetails) {
        this.connectOptions = connectOptions;
        this.rconDetails = rconDetails;
        Logger.debug(connectOptions);
        Logger.debug(rconDetails);
    }

    @Override
    public MinecraftClient call() throws Exception {
        int currentAttempt = 0;

        while (currentAttempt < connectOptions.getMaxRetries() && !Thread.currentThread().isInterrupted()) {
            currentAttempt++;
            Logger.debug("Connection attempt {}", currentAttempt);

            try {
                return MinecraftClient.connect(rconDetails.getHostname(), rconDetails.getPassword(), rconDetails.getPort());
            } catch (Exception e) {
                Logger.debug("Connection attempt failed due to: {}", e.getMessage());
            } finally {
                if (currentAttempt < connectOptions.getMaxRetries()) {
                    sleep();
                } else {
                    Logger.debug("Ran out of retries after {} total attempts", currentAttempt);
                }
            }
        }

        throw new RconConnectException("Unable to connect to Minecraft server after %d retries", currentAttempt - 1);
    }

    private void sleep() {
        try {
            Logger.debug("Pausing for {} ms", connectOptions.getTimeBetweenRetries().toMillis());
            Thread.sleep(connectOptions.getTimeBetweenRetries().toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
