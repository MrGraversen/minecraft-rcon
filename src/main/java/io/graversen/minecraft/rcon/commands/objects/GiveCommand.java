package io.graversen.minecraft.rcon.commands.objects;

import io.graversen.minecraft.rcon.commands.objects.base.BaseTargetedCommand;

public class GiveCommand extends BaseTargetedCommand
{
    private final String item;
    private final int amount;
    private final int data;
    private final String dataTag;

    public GiveCommand(String target, String item, int amount, int data, String dataTag)
    {
        super(target);
        this.item = item;
        this.amount = amount;
        this.data = data;
        this.dataTag = dataTag;
    }

    public String getItem()
    {
        return item;
    }

    public int getAmount()
    {
        return amount;
    }

    public int getData()
    {
        return data;
    }

    public String getDataTag()
    {
        return dataTag;
    }
}
