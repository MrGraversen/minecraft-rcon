package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Weathers;

import java.time.Duration;
import java.util.Objects;

public record WeatherCommand(Weathers weather, long duration) implements ICommand {
    static final long DEFAULT_DURATION = Duration.ofMinutes(5).toSeconds();

    public WeatherCommand(Weathers weather, long duration) {
        this.weather = Objects.requireNonNull(weather);

        if (duration > 1_000_000 || duration <= 0) {
            duration = DEFAULT_DURATION;
        }

        this.duration = duration;
    }

    @Override
    public String command() {
        return "weather " + weather() + " " + duration();
    }
}
