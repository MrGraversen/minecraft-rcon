package io.graversen.minecraft.rcon.commands.objects.base;

public class BaseTargetedCommand
{
    private transient final String getTarget;

    public BaseTargetedCommand(String getTarget)
    {
        this.getTarget = getTarget;
    }

    public String getGetTarget()
    {
        return getTarget;
    }
}
