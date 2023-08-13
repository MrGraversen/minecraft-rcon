package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class ParticleCommand implements ICommand {
    private final String particleName;
    private final Position position;
    private final Position delta;
    private final float speed;
    private final int count;
    private final ParticleMode mode;
    private final Target player;

    public enum ParticleMode {
        NORMAL,
        FORCE
    }

    public ParticleCommand(String particleName) {
        this(particleName, null, null, -1, -1, null, null);
    }

    public ParticleCommand(String particleName, Position position) {
        this(particleName, position, null, -1, -1, null, null);
    }

    public ParticleCommand(String particleName, Position position, Position delta, float speed, int count, ParticleMode mode, Target player) {
        this.particleName = Objects.requireNonNull(particleName);
        this.position = Objects.requireNonNull(position);
        this.delta = Objects.requireNonNull(delta);
        this.speed = speed;
        this.count = count;
        this.mode = mode == null ? ParticleMode.NORMAL : mode;
        this.player = player;
    }

    @Override
    public String command() {
        if (delta == null) {
            return ("particle " + particleName + " " + (position == null ? "" : position)).trim();
        }
        return ("particle " + particleName + " "
                + position + " " + delta + " "
                + speed + " " + count + " "
                + mode + " " + (player == null ? "" : player)).trim();
    }
}
