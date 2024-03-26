package io.graversen.minecraft.rcon.service;

import java.time.Duration;

public record PingResult(Duration latency, boolean success) {}
