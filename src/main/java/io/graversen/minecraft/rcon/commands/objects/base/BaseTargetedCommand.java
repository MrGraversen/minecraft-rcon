package io.graversen.minecraft.rcon.commands.objects.base;

public class BaseTargetedCommand {
    private transient final String target;

    public BaseTargetedCommand(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}
