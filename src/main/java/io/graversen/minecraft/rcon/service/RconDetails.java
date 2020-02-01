package io.graversen.minecraft.rcon.service;

import io.graversen.minecraft.rcon.Defaults;

import java.util.Objects;

public class RconDetails {
    private final String hostname;
    private final int port;
    private final String password;

    public RconDetails(String hostname, int port, String password) {
        this.hostname = Objects.requireNonNull(hostname, "Hostname cannot be null");
        this.port = port;
        this.password = Objects.requireNonNullElse(password, "");
    }

    public static RconDetails localhost() {
        return localhost("");
    }

    public static RconDetails localhost(String password) {
        return new RconDetails("localhost", Defaults.RCON_PORT, password);
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "RconDetails{" +
                "hostname='" + hostname + '\'' +
                ", port=" + port +
                ", password='" + "******" + '\'' +
                '}';
    }
}
