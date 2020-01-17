package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.IMinecraftClient;
import io.graversen.minecraft.rcon.RconConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MinecraftClientService implements Closeable {
    private static Logger LOG = LoggerFactory.getLogger(MinecraftClientService.class);

    private final RconDetails rconDetails;
    private final ScheduledExecutorService executorService;

    private IMinecraftClient minecraftClient;
    private boolean isConnected;

    public MinecraftClientService(RconDetails rconDetails) {
        this.rconDetails = rconDetails;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void connect(ConnectOptions connectOptions) {
        if (!isConnected) {
            try {
                minecraftClient = executorService.submit(new ConnectTask(connectOptions, rconDetails)).get();
                executorService.scheduleWithFixedDelay(new ConnectionWatcherTask(connectionWatcher(), minecraftClient), Duration.ofSeconds(1).toSeconds(), Duration.ofSeconds(1).toSeconds(), TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new RconConnectException(e.getMessage());
            }
        }
    }

    @Override
    public void close() throws IOException {
        LOG.info("Closing");

        if (minecraftClient != null) {
            minecraftClient.close();
        }

        executorService.shutdownNow();
    }

    private void safeClose() {
        try {
            close();
        } catch (Exception e) {
            // Nothing
        }
    }

    private IConnectionWatcher connectionWatcher() {
        return pingResult -> {
            if (!pingResult.isSuccess()) {
                safeClose();
            }
        };
    }
}
