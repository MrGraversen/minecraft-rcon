package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Weathers;
import org.apache.commons.text.StringSubstitutor;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;

public class WeatherCommand implements ICommand {
    static final long DEFAULT_DURATION = Duration.ofMinutes(5).toSeconds();

    private final Weathers weather;
    private final long duration;

    public WeatherCommand(Weathers weather, long duration) {
        this.weather = Objects.requireNonNull(weather);

        if (duration > 1_000_000 || duration <= 0) {
            duration = DEFAULT_DURATION;
        }

        this.duration = duration;
    }

    public Weathers getWeather() {
        return weather;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String command() {
        final var variables = Map.of(
                "weather", getWeather().getWeatherString(),
                "duration", getDuration()
        );

        return StringSubstitutor.replace("weather ${weather} ${duration}", variables);
    }
}
