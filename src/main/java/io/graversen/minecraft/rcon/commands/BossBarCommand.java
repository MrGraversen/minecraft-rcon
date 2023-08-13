package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Color;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;

public class BossBarCommand implements ICommand {
    private final BossBarAction action;
    private final String id;
    private final BossBarSetType setType;
    private final String[] additionalArguments;

    enum BossBarAction {
        ADD,
        GET,
        LIST,
        REMOVE,
        SET;

        String getActionName() {
            return name().toLowerCase();
        }
    }

    public enum BossBarGetType {
        MAX,
        PLAYERS,
        VALUE,
        VISIBLE;

        String getTypeName() {
            return name().toLowerCase();
        }
    }

    enum BossBarSetType {
        COLOR,
        MAX,
        NAME,
        PLAYERS,
        STYLE,
        VALUE,
        VISIBLE;

        String getTypeName() {
            return name().toLowerCase();
        }
    }

    public enum BossBarStyle {
        NOTCHED_6,
        NOTCHED_10,
        NOTCHED_12,
        NOTCHED_20,
        PROGRESS;

        String getStyleName() {
            return name().toLowerCase();
        }
    }

    private BossBarCommand(BossBarAction action, String id, BossBarSetType setType, String... additionalArguments) {
        this.action = action;
        this.id = id;
        this.setType = setType;
        this.additionalArguments = additionalArguments;
    }

    public BossBarCommand add(String id, String name) {
        return new BossBarCommand(BossBarAction.ADD, Objects.requireNonNull(id), null, Objects.requireNonNull(name));
    }

    public BossBarCommand get(String id, BossBarGetType type) {
        return new BossBarCommand(BossBarAction.GET, Objects.requireNonNull(id), null, Objects.requireNonNull(type).getTypeName());
    }

    public BossBarCommand list() {
        return new BossBarCommand(BossBarAction.LIST, null, null);
    }

    public BossBarCommand remove(String id) {
        return new BossBarCommand(BossBarAction.REMOVE, Objects.requireNonNull(id), null);
    }

    public BossBarCommand setColor(String id, Color color) {
        var allowedColors = EnumSet.of(Color.BLUE, Color.GREEN, Color.PINK, Color.PURPLE, Color.RED, Color.WHITE, Color.YELLOW);
        if (!allowedColors.contains(color)) throw new IllegalArgumentException("Invalid color: " + color + ". Allowed colors: " + String.join(", ", allowedColors.stream().map(Color::toString).toList()));
        return new BossBarCommand(BossBarAction.SET, Objects.requireNonNull(id), BossBarSetType.COLOR, Objects.requireNonNull(color).getColorName());
    }

    public BossBarCommand setMax(String id, int max) {
        return new BossBarCommand(BossBarAction.SET, Objects.requireNonNull(id), BossBarSetType.MAX, String.valueOf(max));
    }

    public BossBarCommand setName(String id, String name) {
        return new BossBarCommand(BossBarAction.SET, Objects.requireNonNull(id), BossBarSetType.NAME, Objects.requireNonNull(name));
    }

    public BossBarCommand setPlayers(String id, Target... players) {
        return new BossBarCommand(BossBarAction.SET, Objects.requireNonNull(id), BossBarSetType.PLAYERS, String.join(" ", Arrays.stream(players).map(Target::getTargetString).toList()));
    }

    public BossBarCommand setStyle(String id, BossBarStyle style) {
        return new BossBarCommand(BossBarAction.SET, Objects.requireNonNull(id), BossBarSetType.STYLE, Objects.requireNonNull(style).getStyleName());
    }

    public BossBarCommand setValue(String id, int value) {
        return new BossBarCommand(BossBarAction.SET, Objects.requireNonNull(id), BossBarSetType.VALUE, String.valueOf(value));
    }

    public BossBarCommand setVisible(String id, boolean visible) {
        return new BossBarCommand(BossBarAction.SET, Objects.requireNonNull(id), BossBarSetType.VISIBLE, String.valueOf(visible));
    }

    @Override
    public String command() {
        return "bossbar " + switch (action) {
            case ADD, GET, REMOVE -> action.getActionName() + " " + id + " " + String.join(" ", additionalArguments);
            case LIST -> action.getActionName();
            case SET -> action.getActionName() + " " + id + " " + setType.getTypeName() + " " + String.join(" ", additionalArguments);
        };
    }
}
