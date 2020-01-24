package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.BaseTargetedCommand;
import io.graversen.minecraft.rcon.util.Target;
import io.graversen.minecraft.rcon.util.WhiteListModes;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;
import java.util.Objects;

public class WhiteListCommand extends BaseTargetedCommand {
    private final WhiteListModes whiteListMode;

    public WhiteListCommand(Target target, WhiteListModes whiteListMode) {
        super(target);
        this.whiteListMode = Objects.requireNonNull(whiteListMode);
    }

    public WhiteListModes getWhiteListMode() {
        return whiteListMode;
    }

    @Override
    public String command() {
        switch (getWhiteListMode()) {
            case ADD:
            case REMOVE:
                return StringSubstitutor.replace(
                        "whitelist ${mode} ${target}",
                        Map.of(
                                "mode", getWhiteListMode().getModeName(),
                                "target", getTarget()
                        )
                );
            case LIST:
            case OFF:
            case ON:
            case RELOAD:
                return "whitelist " + getWhiteListMode().getModeName();
            default:
                throw new UnsupportedOperationException("Unsupported whitelist mode: " + getWhiteListMode());
        }
    }
}
