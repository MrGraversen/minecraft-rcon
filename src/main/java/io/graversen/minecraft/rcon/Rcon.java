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

    public void tellRaw(TellRawCommand... tellRawCommand) {
        final String command = "tellraw";
        final String target = tellRawCommand[0].getTarget();

        rconClient.sendRaw(String.format("%s %s %s", command, target, gson.toJson(tellRawCommand)));
    }

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

    public void effect(EffectCommand effectCommand) {
        final String command = "effect";

        if (effectCommand.getClear().isEmpty()) {
            String partialCommand = String.format("%s %s %s %d", command, effectCommand.getTarget(), effectCommand.getEffect(), effectCommand.getSeconds());
            if (effectCommand.getAmplifier() > 0) partialCommand = partialCommand + String.format(" %d", effectCommand.getAmplifier());
            if (effectCommand.isHideParticles()) partialCommand = partialCommand + " true";

            rconClient.sendRaw(partialCommand);
        } else {
            rconClient.sendRaw(String.format("%s %s clear", command, effectCommand.getTarget()));
        }
    }

    public void playSound(PlaySoundCommand playSoundCommand) {
        final String command = "playsound";
        final String source = "player";

        rconClient.sendRaw(String.format("%s %s %s %s", command, playSoundCommand.getSound(), source, playSoundCommand.getTarget()));
    }

    public void difficulty(Difficulties difficulty) {
        final String command = "difficulty";

        rconClient.sendRaw(String.format("%s %s", command, difficulty.getDifficultyName()));
    }

    public void title(TitleCommand titleCommand) {
        final String command = "title";

        rconClient.sendRaw(String.format("%s %s %s %s", command, titleCommand.getTarget(), titleCommand.getPosition(), gson.toJson(titleCommand)));
    }

    public void give(GiveCommand giveCommand) {
        final String command = "give";

        final String data = giveCommand.getData() == 0 ? "" : String.valueOf(giveCommand.getData());
        final String dataTag = giveCommand.getDataTag() != null ? giveCommand.getDataTag() : "";
        int giveCount = (int) Math.floor((double) giveCommand.getAmount() / 64);

        IntStream.rangeClosed(1, giveCount).forEach(i -> rconClient.sendRaw(String.format("%s %s %s %d %s %s", command, giveCommand.getTarget(), giveCommand.getItem(), 64, data, dataTag).trim()));
        if (giveCommand.getAmount() % 64 > 0)
            rconClient.sendRaw(String.format("%s %s %s %d %s %s", command, giveCommand.getTarget(), giveCommand.getItem(), giveCommand.getAmount() % 64, data, dataTag).trim());
    }

    public void whiteList(WhiteListModes whiteListMode, String playerName) {
        final String command = "whitelist";

        switch (whiteListMode) {
            case ADD:
            case REMOVE:
                rconClient.sendRaw(String.format("%s %s %s", command, whiteListMode.getModeName(), playerName));
                break;
            case LIST:
            case OFF:
            case ON:
            case RELOAD:
                rconClient.sendRaw(String.format("%s %s", command, whiteListMode.getModeName()));
                break;
        }
    }

    public void gameMode(GameModes gameMode, String playerName) {
        final String command = "gamemode";

        if (playerName == null) {
            rconClient.sendRaw(String.format("%s %s", command, gameMode.getGameModeString()));
        } else {
            rconClient.sendRaw(String.format("%s %s %s", command, gameMode.getGameModeString(), playerName));
        }
    }

    public String seed() {
        final Future<RconResponse> responseFuture = rconClient.sendRaw("seed");
        final String seedResponse = getResponseString(responseFuture);

        return seedResponse.split(":")[1].trim();
    }

    public void stop() {
        final String command = "stop";

        rconClient.sendRaw(command);
    }

    public void kick(String playerName, String reason) {
        final String command = "kick";
        if (reason == null) reason = "Kicked by Admin";

        rconClient.sendRaw(String.format("%s %s \"%s\"", command, playerName, reason));
    }

    public void ban(String playerName, String reason) {
        final String command = "ban";
        if (reason == null) reason = "Banned by Admin";

        rconClient.sendRaw(String.format("%s %s \"%s\"", command, playerName, reason));
    }

    public void pardon(String playerName) {
        final String command = "pardon";

        rconClient.sendRaw(String.format("%s %s", command, playerName));
    }

    public void op(String playerName) {
        final String command = "op";

        rconClient.sendRaw(String.format("%s %s", command, playerName));
    }

    public void deOp(String playerName) {
        final String command = "deop";

        rconClient.sendRaw(String.format("%s %s", command, playerName));
    }

    public void weather(Weathers weather, int duration) {
        final String command = "weather";

        if (duration > 0) {
            rconClient.sendRaw(String.format("%s %s %d", command, weather.getWeatherString(), duration));
        } else {
            rconClient.sendRaw(String.format("%s %s", command, weather.getWeatherString()));
        }
    }

    public void time(TimeLabels timeLabel) {
        final String command = "time set";

        rconClient.sendRaw(String.format("%s %s", command, timeLabel.getTimeString()));
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
