package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;
import java.util.Objects;

public class BanCommand extends BaseTargetedCommand {
    private final String reason;

    public BanCommand(String target, String reason) {
        super(target);
        this.reason = Objects.requireNonNullElse(reason, "Banned");
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String command() {
        return StringSubstitutor.replace(
                "ban ${target} ${reason}",
                Map.of(
                        "target", getTarget(),
                        "reason", getReason()
                )
        );
    }
}
