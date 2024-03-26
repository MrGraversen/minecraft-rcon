package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.IMinecraftClient;
import io.graversen.minecraft.rcon.MinecraftRcon;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.System.Logger.Level.INFO;
import static java.lang.System.Logger.Level.WARNING;

public class MinecraftRconService implements IMinecraftRconService {
    private static final System.Logger log = System.getLogger(MinecraftRconService.class.getName());

    private final RconDetails rconDetails;
    private final ConnectOptions connectOptions;
    private final ScheduledExecutorService executorService;

    private volatile IMinecraftClient minecraftClient;
    private volatile MinecraftRcon minecraftRcon;

    private volatile boolean shouldClose;
    private volatile boolean shouldConnect;
    private volatile boolean isConnected;

    private CountDownLatch connectionLatch;

    public MinecraftRconService(RconDetails rconDetails, ConnectOptions connectOptions) {
        this.rconDetails = rconDetails;
        this.connectOptions = connectOptions;
        this.executorService = Executors.newScheduledThreadPool(2);
        startConnectionWatcher();
    }

    @Override
    public boolean connectBlocking(Duration timeout) {
        if (isConnected) {
            return true;
        } else {
            try {
                connect();
                return connectionLatch.await(timeout.toSeconds(), TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
    }

    @Override
    public void connect() {
        if (!isConnected && !shouldClose) {
            connectionLatch = new CountDownLatch(1);
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
    public Optional<MinecraftRcon> minecraftRcon() {
        return Optional.ofNullable(minecraftRcon);
    }

    private void safeClose(String reason) {
        try {
            log.log(INFO, "Closing with reason: " + reason);
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
                if (!pingResult.success() && shouldConnect) {
                    if (isConnected) {
                        log.log(WARNING, "Connection broken - resetting");
                        isConnected = false;
                        minecraftClient = null;
                        minecraftRcon = null;
                    }
                    doConnect();
                }
            }
        };
    }

    private void doConnect() {
        try {
            minecraftClient = executorService.submit(new ConnectTask(connectOptions, rconDetails)).get();
            minecraftRcon = new MinecraftRcon(minecraftClient);
            isConnected = true;
            connectionLatch.countDown();
        } catch (Exception e) {
            safeClose(e.getMessage());
        }
    }

    private void startConnectionWatcher() {
        final long intervalSeconds = connectOptions.connectionWatcherInterval().toSeconds();
        final Runnable watcherTask = new ConnectionWatcherTask(connectionWatcher());
        executorService.scheduleWithFixedDelay(watcherTask, intervalSeconds, intervalSeconds, TimeUnit.SECONDS);
    }
}
