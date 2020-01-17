package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.IMinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MinecraftClientService implements IMinecraftClientService {
    private static Logger LOG = LoggerFactory.getLogger(MinecraftClientService.class);

    private final RconDetails rconDetails;
    private final ConnectOptions connectOptions;
    private final ScheduledExecutorService executorService;

    private IMinecraftClient minecraftClient;

    private volatile boolean shouldClose;
    private volatile boolean shouldConnect;
    private volatile boolean isConnected;

    public MinecraftClientService(RconDetails rconDetails, ConnectOptions connectOptions) {
        this.rconDetails = rconDetails;
        this.connectOptions = connectOptions;
        this.executorService = Executors.newScheduledThreadPool(2);
        startConnectionWatcher();
    }

    @Override
    public void connect() {
        if (!isConnected && !shouldClose) {
            shouldConnect = true;
        }
    }

    @Override
    public void disconnect() {
        shouldConnect = false;
        shouldClose = true;
        safeClose("Disconnect invoked");
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public Optional<IMinecraftClient> minecraftClient() {
        return Optional.ofNullable(minecraftClient);
    }

    private void safeClose(String reason) {
        try {
            LOG.info("Closing with reason: {}", reason);
            isConnected = false;

            if (minecraftClient != null) {
                minecraftClient.close();
            }

            executorService.shutdownNow();

        } catch (Exception e) {
            // Nothing
        }
    }

    private IConnectionWatcher connectionWatcher() {
        return new IConnectionWatcher() {
            @Override
            public boolean onTestConnection() {
                if (minecraftClient != null) {
                    return minecraftClient.isConnected(Duration.ofSeconds(1));
                }

                return false;
            }

            @Override
            public void onPingResult(PingResult pingResult) {
                if (!pingResult.isSuccess() && shouldConnect) {
                    if (isConnected) {
                        isConnected = false;
                        minecraftClient = null;
                        LOG.warn("Connection broken - reconnecting");
                    }
                    doConnect();
                }
            }
        };
    }

    private void doConnect() {
        try {
            minecraftClient = executorService.submit(new ConnectTask(connectOptions, rconDetails)).get();
            isConnected = true;
        } catch (Exception e) {
            safeClose(e.getMessage());
        }
    }

    private void startConnectionWatcher() {
        final long intervalSeconds = connectOptions.getConnectionWatcherInterval().toSeconds();
        final Runnable watcherTask = new ConnectionWatcherTask(connectionWatcher());
        executorService.scheduleWithFixedDelay(watcherTask, intervalSeconds, intervalSeconds, TimeUnit.SECONDS);
    }
}
