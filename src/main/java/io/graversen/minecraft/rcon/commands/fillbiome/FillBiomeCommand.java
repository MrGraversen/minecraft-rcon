package io.graversen.minecraft.rcon.commands.fillbiome;

import io.graversen.minecraft.rcon.commands.base.Base3DPositionalCommand;
import io.graversen.minecraft.rcon.util.Biome;
import io.graversen.minecraft.rcon.util.FillMode;
import io.graversen.minecraft.rcon.util.Position;

import java.util.Objects;

public class FillBiomeCommand extends Base3DPositionalCommand {
    private final Biome biome;
    private final Biome replaceBiome;
    private final FillMode fillMode;

    public FillBiomeCommand(Position position1, Position position2, Biome biome, Biome replaceBiome, FillMode fillMode) {
        super(position1, position2);
        this.biome = biome;
        this.replaceBiome = replaceBiome;
        this.fillMode = fillMode;
    }

    public Biome getBiome() {
        return biome;
    }

    public Biome getReplaceBiome() {
        return replaceBiome;
    }

    public FillMode getFillMode() {
        return fillMode;
    }

    @Override
    public String command() {
        return ("fill " +
                getPosition1() + " " +
                getPosition2() + " " +
                getBiome() + " " +
                getFillMode().getFillModesString() + " " +
                Objects.requireNonNullElse(getReplaceBiome(), "")).trim();
    }
}
