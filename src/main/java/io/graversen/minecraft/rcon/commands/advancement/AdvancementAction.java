package io.graversen.minecraft.rcon.commands.advancement;

public enum AdvancementAction {
    GRANT,
    REVOKE;

    public String getAdvancementActionName() {
        return name().toLowerCase();
    }
}
