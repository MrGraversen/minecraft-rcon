package io.graversen.minecraft.rcon.service;

import java.time.Duration;

record PingResult(Duration latency, boolean success) {}
