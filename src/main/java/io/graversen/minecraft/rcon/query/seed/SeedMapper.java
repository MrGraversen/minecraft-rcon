package io.graversen.minecraft.rcon.query.seed;

import io.graversen.minecraft.rcon.RconResponse;
import io.graversen.minecraft.rcon.query.IRconResponseMapper;

public class SeedMapper implements IRconResponseMapper<Seed> {
    @Override
    public Seed apply(RconResponse rconResponse) {
        return new Seed(rconResponse.getResponseString().split(":")[1]);
    }
}
