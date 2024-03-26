package io.graversen.minecraft.rcon.commands;

import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.util.Color;

import java.util.Objects;

public class TeamCommand implements ICommand {
    public enum Visibility {
        ALWAYS("always"),
        NEVER("never"),
        HIDE_FOR_OTHER_TEAMS("hideForOtherTeams"),
        HIDE_FOR_OWN_TEAM("hideForOwnTeam");

        private final String name;

        Visibility(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum CollisionRule {
        ALWAYS("always"),
        NEVER("never"),
        PUSH_OTHER_TEAMS("pushOtherTeams"),
        PUSH_OWN_TEAM("pushOwnTeam");

        private final String name;

        CollisionRule(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private final String command;

    private TeamCommand(String command) {
        this.command = command.trim();
    }

    static TeamCommand list(String teamName) {
        return new TeamCommand("list " + Objects.requireNonNull(teamName));
    }

    static TeamCommand add(String teamName, String displayName) {
        return new TeamCommand("add " + Objects.requireNonNull(teamName) + " " + Objects.requireNonNull(displayName));
    }

    static TeamCommand remove(String teamName) {
        return new TeamCommand("remove " + Objects.requireNonNull(teamName));
    }

    static TeamCommand empty(String teamName) {
        return new TeamCommand("empty " + Objects.requireNonNull(teamName));
    }

    static TeamCommand join(String teamName, String... members) {
        return new TeamCommand("join " + Objects.requireNonNull(teamName) + " " + String.join(" ", members));
    }

    static TeamCommand leave(String... playerNames) {
        return new TeamCommand("leave " + String.join(" ", playerNames));
    }

    static TeamCommand modifyDisplayName(String teamName, String displayName) {
        return new TeamCommand("modify " + Objects.requireNonNull(teamName) + " displayName " + Objects.requireNonNull(displayName));
    }

    static TeamCommand modifyColor(String teamName, Color color) {
        return new TeamCommand("modify " + Objects.requireNonNull(teamName) + " color " + Objects.requireNonNull(color));
    }

    static TeamCommand modifyFriendlyFire(String teamName, boolean friendlyFire) {
        return new TeamCommand("modify " + Objects.requireNonNull(teamName) + " friendlyFire " + friendlyFire);
    }

    static TeamCommand modifySeeFriendlyInvisibles(String teamName, boolean seeFriendlyInvisibles) {
        return new TeamCommand("modify " + Objects.requireNonNull(teamName) + " seeFriendlyInvisibles " + seeFriendlyInvisibles);
    }

    static TeamCommand modifyNametagVisibility(String teamName, Visibility nametagVisibility) {
        return new TeamCommand("modify " + Objects.requireNonNull(teamName) + " nametagVisibility " + Objects.requireNonNullElse(nametagVisibility, Visibility.ALWAYS));
    }

    static TeamCommand modifyDeathMessageVisibility(String teamName, Visibility deathMessageVisibility) {
        return new TeamCommand("modify " + Objects.requireNonNull(teamName) + " deathMessageVisibility " + Objects.requireNonNullElse(deathMessageVisibility, Visibility.ALWAYS));
    }

    static TeamCommand modifyCollisionRule(String teamName, CollisionRule collisionRule) {
        return new TeamCommand("modify " + Objects.requireNonNull(teamName) + " collisionRule " + Objects.requireNonNullElse(collisionRule, CollisionRule.ALWAYS));
    }

    static TeamCommand modifyPrefix(String teamName, String prefix) {
        return new TeamCommand("modify " + Objects.requireNonNull(teamName) + " prefix " + Objects.requireNonNull(prefix));
    }

    static TeamCommand modifySuffix(String teamName, String suffix) {
        return new TeamCommand("modify " + Objects.requireNonNull(teamName) + " suffix " + Objects.requireNonNull(suffix));
    }

    @Override
    public String command() {
        return "team " + command;
    }
}
