package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.MinecraftClient;
import io.graversen.minecraft.rcon.RconConnectException;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.concurrent.Callable;

import static java.lang.System.Logger.Level.*;

class ConnectTask implements Callable<MinecraftClient> {
    private static final Logger log = System.getLogger(ConnectTask.class.getName());

    private final ConnectOptions connectOptions;
    private final RconDetails rconDetails;

    ConnectTask(ConnectOptions connectOptions, RconDetails rconDetails) {
        this.connectOptions = connectOptions;
        this.rconDetails = rconDetails;
        log.log(DEBUG, connectOptions);
    }

    @Override
    public MinecraftClient call() {
        int currentAttempt = 0;

        while (currentAttempt < connectOptions.maxRetries() && !Thread.currentThread().isInterrupted()) {
            currentAttempt++;
            log.log(DEBUG, "Connection attempt " + currentAttempt);

            try {
                return MinecraftClient.connect(rconDetails.hostname(), rconDetails.password(), rconDetails.port());
            } catch (Exception e) {
                log.log(ERROR, "Connection attempt failed", e);
            } finally {
                if (currentAttempt < connectOptions.maxRetries()) {
                    sleep();
                } else {
                    log.log(WARNING,"Ran out of retries after " + currentAttempt + " total attempts");
                }
            }
        }

        throw new RconConnectException("Unable to connect to Minecraft server after %d retries", currentAttempt - 1);
    }

    private void sleep() {
        try {
            log.log(DEBUG, "Pausing for " + connectOptions.timeBetweenRetries().toMillis() + " ms");
            Thread.sleep(connectOptions.timeBetweenRetries().toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.log(Level.ERROR, "Interrupted while sleeping", e);
        }
    }
}
