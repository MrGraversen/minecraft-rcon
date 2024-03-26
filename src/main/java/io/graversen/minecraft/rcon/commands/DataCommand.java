package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Position;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Arrays;
import java.util.Objects;

public class DataCommand implements ICommand {

    public static class DataTarget {
        private DataTarget() {}

        public static String block(Position target) {
            return "block " + target;
        }

        public static String entity(Target target) {
            return "entity " + target;
        }

        public static String storage(String target) {
            return "storage " + target;
        }
    }

    public static class DataModifyOperation {
        private DataModifyOperation() {}

        public static String append() {
            return "append";
        }

        public static String insert(int index) {
            return "insert " + index;
        }

        public static String merge() {
            return "merge";
        }

        public static String prepend() {
            return "prepend";
        }

        public static String set() {
            return "set";
        }
    }

    public static class DataModifyAction {
        private DataModifyAction() {}

        public static String from(DataTarget target, String sourcePath) {
            return "from " + Objects.requireNonNull(target) + " " + sourcePath;
        }

        public static String string(DataTarget target, String sourcePath, int start, int end) {
            return "value " + Objects.requireNonNull(target) + " " + sourcePath + " "
                    + (start == -1 ? "" : start) + " " + (end == -1 ? "" : end);
        }

        public static String value(String value) {
            return "value " + value;
        }
    }

    private final DataTarget target;
    private final String[] args;

    private DataCommand(DataTarget target, String[] args) {
        this.target = Objects.requireNonNull(target);
        this.args = args;
    }

    public static DataCommand get(DataTarget target, String path, String scale) {
        return new DataCommand(target, new String[] { "get", path, scale });
    }

    public static DataCommand merge(DataTarget target, String nbtData) {
        return new DataCommand(target, new String[] { "merge", nbtData });
    }

    public static DataCommand modify(DataTarget target, String path, DataModifyOperation operation, DataModifyAction action) {
        return new DataCommand(target, new String[] { "modify", path, operation.toString(), action.toString() });
    }

    public static DataCommand remove(DataTarget target, String path) {
        return new DataCommand(target, new String[] { "remove", path });
    }

    @Override
    public String command() {
        return "data " + target + " " + String.join(" ", Arrays.stream(args)
                .filter(Objects::nonNull)
                .toArray(String[]::new)
        );
    }
}
