package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

import java.util.Objects;

public class BanListCommand implements ICommand {
    private final BanListType banListType;

    public enum BanListType {
        IPS, PLAYERS, ALL;

        public String getBanListType() {
            return this == ALL ? "" : name().toLowerCase();
        }
    }

    public BanListCommand() {
        this(BanListType.ALL);
    }

    public BanListCommand(BanListType banListType) {
        this.banListType = Objects.requireNonNull(banListType);
    }

    @Override
    public String command() {
        return ("banlist " + banListType.getBanListType()).trim();
    }
}
