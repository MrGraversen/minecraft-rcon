package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

import java.util.Objects;

public class PardonIpCommand implements ICommand {
    private final String ip;

    public PardonIpCommand(String ip) {
        this.ip = Objects.requireNonNullElse(ip, "");
    }

    @Override
    public String command() {
        return ("pardon-ip " + ip).trim();
    }
}
