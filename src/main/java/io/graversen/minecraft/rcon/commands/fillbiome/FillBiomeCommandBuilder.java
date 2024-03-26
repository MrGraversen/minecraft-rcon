package io.graversen.minecraft.rcon.commands.fillbiome;

import io.graversen.minecraft.rcon.commands.base.ICommandBuilder;
import io.graversen.minecraft.rcon.util.Biome;
import io.graversen.minecraft.rcon.util.FillMode;
import io.graversen.minecraft.rcon.util.Position;

import java.util.Objects;

public class FillBiomeCommandBuilder implements ICommandBuilder<FillBiomeCommand> {
    private final Position position1;
    private final Position position2;
    private Biome biome;
    private Biome replaceBiome;
    private FillMode fillMode;

    private FillBiomeCommandBuilder(Position position1, Position position2) {
        this.position1 = position1;
        this.position2 = position2;
    }

    public static FillBiomeCommandBuilder bounds(Position position1, Position position2) {
        return new FillBiomeCommandBuilder(
                Objects.requireNonNull(position1),
                Objects.requireNonNull(position2)
        );
    }

    public FillBiomeCommand replace(Biome biome) {
        this.biome = biome;
        this.fillMode = FillMode.REPLACE;
        return build();
    }

    public FillBiomeCommand replace(Biome biome, Biome replaceBiome) {
        this.biome = biome;
        this.replaceBiome = replaceBiome;
        this.fillMode = FillMode.REPLACE;
        return build();
    }

    @Override
    public boolean validate() {
        return Objects.nonNull(position1) && Objects.nonNull(position2) && Objects.nonNull(biome) && Objects.nonNull(fillMode);
    }

    @Override
    public FillBiomeCommand build() {
        if (validate()) {
            return new FillBiomeCommand(position1, position2, biome, replaceBiome, fillMode);
        } else {
            throw new IllegalStateException("Could not construct valid FillBiomeCommand");
        }
    }
}
