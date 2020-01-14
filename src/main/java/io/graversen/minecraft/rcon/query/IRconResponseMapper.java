package io.graversen.minecraft.rcon.query;

import io.graversen.minecraft.rcon.RconCommandException;
import io.graversen.minecraft.rcon.RconResponse;

import java.util.function.Function;

@FunctionalInterface
public interface IRconResponseMapper<T> extends Function<RconResponse, T> {
    default T safeApply(RconResponse rconResponse) {
        try {
            return apply(rconResponse);
        } catch (Exception e) {
            throw new RconCommandException(e, "Could not convert response");
        }
    }
}
