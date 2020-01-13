package io.graversen.minecraft.rcon.commands.base;

public abstract class BaseTargetedCommand extends BaseCommand {
    private transient final String target;

    public BaseTargetedCommand(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}
