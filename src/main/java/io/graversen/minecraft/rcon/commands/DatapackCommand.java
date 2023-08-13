package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;

import java.util.Objects;

public class DatapackCommand implements ICommand {
    private final String name;
    private final DatapackAction action;
    private boolean advancedEnabled;
    private final boolean firstOrLast;
    private final boolean beforeOrAfter;
    private final String existing;
    private final DatapackListType listingType;

    private enum DatapackAction {
        ENABLE,
        DISABLE,
        LIST;

        public String getDatapackActionName() {
            return name().toLowerCase();
        }
    }

    public enum DatapackListType {
        ALL,
        AVAILABLE,
        ENABLED;

        public String getDatapackListTypeName() {
            return this == ALL ? "" : name().toLowerCase();
        }
    }

    private DatapackCommand(String name, DatapackAction action, boolean advancedEnabled, boolean firstOrLast, boolean beforeOrAfter, String existing, DatapackListType listingType) {
        this.name = name;
        this.action = action;
        this.advancedEnabled = advancedEnabled;
        this.firstOrLast = firstOrLast;
        this.beforeOrAfter = beforeOrAfter;
        this.existing = existing;
        this.listingType = listingType;
    }

    public static DatapackCommand disable(String name) {
        return new DatapackCommand(Objects.requireNonNull(name), DatapackAction.DISABLE, false, false, false, null, null);
    }

    public static DatapackCommand enable(String name) {
        return new DatapackCommand(Objects.requireNonNull(name), DatapackAction.ENABLE, false, false, false, null, null);
    }

    public static DatapackCommand enable(String name, boolean first) {
        return new DatapackCommand(Objects.requireNonNull(name), DatapackAction.ENABLE, true, first, false, null, null);
    }

    public static DatapackCommand enable(String name, boolean before, String existing) {
        return new DatapackCommand(Objects.requireNonNull(name), DatapackAction.ENABLE, true, false, before, Objects.requireNonNull(existing), null);
    }

    public static DatapackCommand list(DatapackListType filter) {
        return new DatapackCommand(null, DatapackAction.LIST, false, false, false, null, Objects.requireNonNull(filter));
    }

    @Override
    public String command() {
        return "datapack " + switch (action) {
            case ENABLE -> action.getDatapackActionName() + " "
                    + name + " "
                    + (advancedEnabled
                        ? (beforeOrAfter ? "before" : "after") + " " + existing
                        : firstOrLast ? "first" : "last"
                    );
            case DISABLE -> action.getDatapackActionName() + " " + name;
            case LIST -> (action.getDatapackActionName() + " " + listingType.getDatapackListTypeName()).trim();
        };
    }
}
