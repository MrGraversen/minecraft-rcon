package io.graversen.minecraft.rcon;

import com.google.gson.Gson;
import io.graversen.minecraft.rcon.commands.PlaySoundCommand;
import io.graversen.minecraft.rcon.commands.effect.EffectCommand;
import io.graversen.minecraft.rcon.commands.give.GiveCommand;
import io.graversen.minecraft.rcon.commands.tellraw.TellRawCommand;
import io.graversen.minecraft.rcon.commands.title.TitleCommand;
import io.graversen.minecraft.rcon.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Rcon {
    private final static int DEFAULT_TIMEOUT = 5000;

    private final GameRuleWrapper gameRuleWrapper;
    private final Gson gson;
    private final IRconClient rconClient;

    public Rcon(IRconClient rconClient) {
        this.gameRuleWrapper = new GameRuleWrapper();
        this.gson = new Gson();
        this.rconClient = rconClient;
    }

    public GameRuleWrapper gameRules() {
        return gameRuleWrapper;
    }

    // TODO
    public List<String> playerList() {
        final String command = "list";
        final Future<RconResponse> rconResponse = rconClient.sendRaw(command);

        final String responseString = getResponseString(rconResponse);
        final String[] players = responseString.split(":");

        if (players.length == 2) {
            return Arrays.asList(players[1].split(","));
        } else {
            return new ArrayList<>();
        }

    }

    // TODO
    public String seed() {
        final Future<RconResponse> responseFuture = rconClient.sendRaw("seed");
        final String seedResponse = getResponseString(responseFuture);

        return seedResponse.split(":")[1].trim();
    }

    private String getResponseString(Future<RconResponse> responseFuture) {
        try {
            return responseFuture.get(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS).getResponseString();
        } catch (Exception e) {
            throw new RuntimeException("Unable to complete RCON command execution", e);
        }
    }

    public class GameRuleWrapper {
        private final String command = "gamerule";

        private GameRuleWrapper() {

        }

        public void setGameRule(GameRules gameRule, boolean value) {
            doSetGameRule(gameRule.getGameRuleName(), String.valueOf(value));
        }

        public void setGameRule(GameRules gameRule, int value) {
            doSetGameRule(gameRule.getGameRuleName(), String.valueOf(value));
        }

        public String getGameRule(GameRules gameRule) {
            final Future<RconResponse> responseFuture = rconClient.sendRaw(String.format("%s %s", command, gameRule.getGameRuleName()));
            return getResponseString(responseFuture);
        }

        private void doSetGameRule(String gameRule, String value) {
            rconClient.sendRaw(String.format("%s %s %s", command, gameRule, value));
        }
    }
}
