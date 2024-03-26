package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Item;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public class ClearCommand extends BaseTargetedCommand {
    private final Item itemName;
    private final int maxCount;

    public ClearCommand(Target target) {
        super(target);
        itemName = null;
        maxCount = -1;
    }

    public ClearCommand(Target target, Item itemName) {
        super(target);
        this.itemName = Objects.requireNonNull(itemName);
        maxCount = -1;
    }

    public ClearCommand(Target target, Item itemName, int maxCount) {
        super(target);
        this.itemName = Objects.requireNonNull(itemName);
        this.maxCount = maxCount;
    }

    @Override
    public String command() {
        return ("clear " + getTarget()
                + (itemName != null ? " " + itemName : "")
                + (itemName != null && maxCount >= 0 ? " " + maxCount : "")
        ).trim();
    }
}
