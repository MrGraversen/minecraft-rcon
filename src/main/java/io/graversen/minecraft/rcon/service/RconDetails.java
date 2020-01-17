package io.graversen.minecraft.rcon.service;

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

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }
}
