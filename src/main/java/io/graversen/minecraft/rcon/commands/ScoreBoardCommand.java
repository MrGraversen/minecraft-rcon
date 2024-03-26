package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Target;

import java.util.Objects;

public abstract class ScoreBoardCommand {
    private ScoreBoardCommand() {}

    static class Objectives extends ScoreBoardCommand implements ICommand {
        private final String subcommand;

        private Objectives(String subcommand) {
            this.subcommand = subcommand.trim();
        }

        static Objectives list() {
            return new Objectives("list");
        }

        static Objectives add(String objectiveName, String objectiveCriteria) {
            return add(objectiveName, objectiveCriteria, "");
        }

        static Objectives add(String objectiveName, String objectiveCriteria, String displayName) {
            return new Objectives("add " + Objects.requireNonNull(objectiveName) + " " + Objects.requireNonNull(objectiveCriteria) + " " + displayName);
        }

        static Objectives remove(String objectiveName) {
            return new Objectives("remove " + objectiveName);
        }

        static Objectives setDisplay(String slot) {
            return setDisplay(slot, "");
        }

        static Objectives setDisplay(String slot, String objectiveName) {
            return new Objectives("setdisplay " + Objects.requireNonNull(slot) + " " + objectiveName);
        }

        static Objectives modifyDisplayName(String objectiveName, String displayName) {
            return new Objectives("modify " + Objects.requireNonNull(objectiveName) + " displayname " + Objects.requireNonNull(displayName));
        }

        enum RenderType {
            HEARTS,
            INTEGER;

            String renderType() {
                return name().toLowerCase();
            }
        }

        static Objectives modifyRenderType(String objectiveName, RenderType renderType) {
            return new Objectives("modify " + Objects.requireNonNull(objectiveName) + " rendertype " + Objects.requireNonNull(renderType).renderType());
        }

        @Override
        public String command() {
            return "scoreboard objectives " + subcommand;
        }
    }

    static class Players extends ScoreBoardCommand implements ICommand {
        private final String subcommand;

        private Players(String subcommand) {
            this.subcommand = subcommand.trim();
        }

        static Players list() {
            return new Players("list");
        }

        static Players list(Target target) {
            return new Players("list " + Objects.requireNonNull(target));
        }

        static Players get(Target target, String objectiveName) {
            return new Players("get " + Objects.requireNonNull(target) + " " + Objects.requireNonNull(objectiveName));
        }

        static Players set(Target target, String objectiveName, int score) {
            return new Players("set " + Objects.requireNonNull(target) + " " + Objects.requireNonNull(objectiveName) + " " + score);
        }

        static Players add(Target target, String objectiveName, int score) {
            return new Players("add " + Objects.requireNonNull(target) + " " + Objects.requireNonNull(objectiveName) + " " + score);
        }

        static Players remove(Target target, String objectiveName, int score) {
            return new Players("remove " + Objects.requireNonNull(target) + " " + Objects.requireNonNull(objectiveName) + " " + score);
        }

        static Players reset(Target target) {
            return reset(target, "");
        }

        static Players reset(Target target, String objectiveName) {
            return new Players("reset " + Objects.requireNonNull(target) + " " + Objects.requireNonNull(objectiveName));
        }

        static Players enable(Target target, String objectiveName) {
            return new Players("enable " + Objects.requireNonNull(target) + " " + Objects.requireNonNull(objectiveName));
        }

        static Players operation(Target target, String targetObjectiveName, String operation, String source, String sourceObjectiveName) {
            return new Players("operation " + Objects.requireNonNull(target) + " " + Objects.requireNonNull(targetObjectiveName) + " " + Objects.requireNonNull(operation) + " " + Objects.requireNonNull(source) + " " + Objects.requireNonNull(sourceObjectiveName));
        }

        @Override
        public String command() {
            return "scoreboard players " + subcommand;
        }
    }
}
