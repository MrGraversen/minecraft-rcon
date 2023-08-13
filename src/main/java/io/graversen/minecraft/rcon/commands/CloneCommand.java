package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.Base3DPositionalCommand;
import io.graversen.minecraft.rcon.util.Dimension;
import io.graversen.minecraft.rcon.util.Position;

import java.util.Objects;

public class CloneCommand extends Base3DPositionalCommand {
    private final Dimension fromDimension;
    private final Dimension toDimension;
    private final Position destination;
    private final CloneMode cloneMode;
    private final CloneType cloneType;

    public static class CloneMode {
        private final String id;
        private final String mode;
        private final boolean isFiltered;

        private CloneMode(String id, String mode, boolean isFiltered) {
            this.id = id;
            this.mode = mode;
            this.isFiltered = isFiltered;
        }

        public CloneMode masked() {
            return new CloneMode(null, "masked", false);
        }

        public CloneMode filtered(String id) {
            return new CloneMode(id, "filtered", true);
        }

        public CloneMode replace() {
            return new CloneMode(null, "replace", false);
        }

        String getMode() {
            return mode + (isFiltered ? " " + id : "");
        }
    }

    public enum CloneType {
        FORCE,
        MOVE,
        NORMAL;

        String getCloneType() {
            return name().toLowerCase();
        }
    }

    public CloneCommand(Dimension fromDimension, Position begin, Position end, Dimension toDimension, Position destination, CloneMode cloneMode, CloneType cloneType) {
        super(begin, end);
        if (cloneType != null && cloneMode == null) {
            throw new IllegalArgumentException("Clone type specified without clone mode");
        }
        this.fromDimension = fromDimension;
        this.toDimension = toDimension;
        this.destination = Objects.requireNonNull(destination);
        this.cloneMode = cloneMode;
        this.cloneType = cloneType;
    }

    @Override
    public String command() {
        return ("clone "
                + (fromDimension != null ? "from " + fromDimension.getNamespacedDimensionString() + " " : "")
                + getPosition1() + " " + getPosition2() + " "
                + (toDimension != null ? "to " + toDimension.getNamespacedDimensionString() + " " : "")
                + destination + " "
                + (cloneMode != null ? cloneMode.getMode() + " " : "")
                + (cloneType != null ? cloneType.getCloneType() : "")).trim();
    }
}
