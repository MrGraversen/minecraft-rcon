package io.graversen.minecraft.rcon.commands.fill;

import io.graversen.minecraft.rcon.commands.base.Base3DPositionalCommand;
import io.graversen.minecraft.rcon.util.Block;
import io.graversen.minecraft.rcon.util.FillMode;
import io.graversen.minecraft.rcon.util.Position;

import java.util.Objects;

public class FillCommand extends Base3DPositionalCommand {
    private final Block block;
    private final Block replaceBlock;
    private final FillMode fillMode;

    public FillCommand(Position position1, Position position2, Block block, Block replaceBlock, FillMode fillMode) {
        super(position1, position2);
        this.block = block;
        this.replaceBlock = replaceBlock;
        this.fillMode = fillMode;
    }

    public Block getBlock() {
        return block;
    }

    public Block getReplaceBlock() {
        return replaceBlock;
    }

    public FillMode getFillMode() {
        return fillMode;
    }

    @Override
    public String command() {
        return ("fill " +
                getPosition1() + " " +
                getPosition2() + " " +
                getBlock() + " " +
                getFillMode().getFillModesString() + " " +
                Objects.requireNonNullElse(getReplaceBlock(), "")).trim();
    }
}
