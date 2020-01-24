package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;
import java.util.Objects;

public class KickCommand extends BaseTargetedCommand {
    private final String reason;

    public KickCommand(Target target, String reason) {
        super(target);
        this.reason = Objects.requireNonNullElse(reason, "Kicked");
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String command() {
        return StringSubstitutor.replace(
                "kick ${target} ${reason}",
                Map.of(
                        "target", getTarget(),
                        "reason", getReason()
                )
        );
    }
}
