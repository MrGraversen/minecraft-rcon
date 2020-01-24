package io.graversen.minecraft.rcon.commands.fill;

import io.graversen.minecraft.rcon.commands.base.ICommandBuilder;
import io.graversen.minecraft.rcon.util.Block;
import io.graversen.minecraft.rcon.util.FillModes;
import io.graversen.minecraft.rcon.util.Position;

import java.util.Objects;

public class FillCommandBuilder implements ICommandBuilder<FillCommand> {
    private final Position position1;
    private final Position position2;
    private Block block;
    private Block replaceBlock;
    private FillModes fillMode;

    private FillCommandBuilder(Position position1, Position position2) {
        this.position1 = position1;
        this.position2 = position2;
    }

    public static FillCommandBuilder bounds(Position position1, Position position2) {
        return new FillCommandBuilder(
                Objects.requireNonNull(position1),
                Objects.requireNonNull(position2)
        );
    }

    public FillCommand replace(Block block) {
        this.block = block;
        this.fillMode = FillModes.REPLACE;
        return build();
    }

    public FillCommand replace(Block block, Block replaceBlock) {
        this.block = block;
        this.replaceBlock = replaceBlock;
        this.fillMode = FillModes.REPLACE;
        return build();
    }

    public FillCommand outline(Block block) {
        this.block = block;
        this.fillMode = FillModes.OUTLINE;
        return build();
    }

    public FillCommand keep(Block block) {
        this.block = block;
        this.fillMode = FillModes.KEEP;
        return build();
    }

    public FillCommand hollow(Block block) {
        this.block = block;
        this.fillMode = FillModes.HOLLOW;
        return build();
    }

    public FillCommand destroy(Block block) {
        this.block = block;
        this.fillMode = FillModes.DESTROY;
        return build();
    }

    @Override
    public boolean validate() {
        return Objects.nonNull(position1) && Objects.nonNull(position2) && Objects.nonNull(block) && Objects.nonNull(fillMode);
    }

    @Override
    public FillCommand build() {
        if (validate()) {
            return new FillCommand(position1, position2, block, replaceBlock, fillMode);
        } else {
            throw new IllegalStateException("Could not construct valid FillCommand");
        }
    }
}
