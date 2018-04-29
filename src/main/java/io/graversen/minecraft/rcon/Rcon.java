package io.graversen.minecraft.rcon;

import com.google.gson.Gson;
import io.graversen.minecraft.rcon.commands.objects.EffectCommand;
import io.graversen.minecraft.rcon.commands.objects.TellRawCommand;
import io.graversen.minecraft.rcon.commands.objects.TitleCommand;
import io.graversen.minecraft.rcon.util.Difficulties;
import io.graversen.minecraft.rcon.util.GameRules;
import io.graversen.minecraft.rcon.util.WhiteListModes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Rcon
{
    private final static int DEFAULT_TIMEOUT = 5000;

    private final GameRuleWrapper gameRuleWrapper;
    private final Gson gson;
    private final IRconClient rconClient;

    public Rcon(IRconClient rconClient)
    {
        this.gameRuleWrapper = new GameRuleWrapper();
        this.gson = new Gson();
        this.rconClient = rconClient;
    }

    public GameRuleWrapper gameRules()
    {
        return gameRuleWrapper;
    }

    public void tellRaw(TellRawCommand... tellRawCommand)
    {
        final String command = "tellraw";
        final String target = tellRawCommand[0].getTarget();

        rconClient.sendRaw(String.format("%s %s %s", command, target, gson.toJson(tellRawCommand)));
    }

    public List<String> playerList()
    {
        final String command = "list";
        final Future<RconResponse> rconResponse = rconClient.sendRaw(command);

        final String responseString = getResponseString(rconResponse);
        final String[] players = responseString.split(":");

        if (players.length == 2)
        {
            return Arrays.asList(players[1].split(","));
        }
        else
        {
            return new ArrayList<>();
        }

    }

    public void effect(EffectCommand effectCommand)
    {
        final String command = "effect";

        if (effectCommand.getClear().isEmpty())
        {
            String partialCommand = String.format("%s %s %s %d", command, effectCommand.getTarget(), effectCommand.getEffect(), effectCommand.getSeconds());
            if (effectCommand.getAmplifier() > 0) partialCommand = partialCommand + String.format(" %d", effectCommand.getAmplifier());
            if (effectCommand.isHideParticles()) partialCommand = partialCommand + " true";

            rconClient.sendRaw(partialCommand);
        }
        else
        {
            rconClient.sendRaw(String.format("%s %s clear", command, effectCommand.getTarget()));
        }
    }

    public void difficulty(Difficulties difficulty)
    {
        final String command = "difficulty";

        rconClient.sendRaw(String.format("%s %s", command, difficulty.getDifficultyName()));
    }

    public void title(TitleCommand titleCommand)
    {
        final String command = "title";

        rconClient.sendRaw(String.format("%s %s %s %s", command, titleCommand.getTarget(), titleCommand.getPosition(), gson.toJson(titleCommand)));
    }

    public void whiteList(WhiteListModes whiteListMode, String playerName)
    {
        final String command = "whitelist";

        switch (whiteListMode)
        {
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

    public String seed()
    {
        final Future<RconResponse> responseFuture = rconClient.sendRaw("seed");
        final String seedResponse = getResponseString(responseFuture);

        return seedResponse.split(":")[1].trim();
    }

    public void kick(String playerName, String reason)
    {
        final String command = "kick";
        if (reason == null) reason = "Kicked by Admin";

        rconClient.sendRaw(String.format("%s %s \"%s\"", command, playerName, reason));
    }

    public void ban(String playerName, String reason)
    {
        final String command = "ban";
        if (reason == null) reason = "Banned by Admin";

        rconClient.sendRaw(String.format("%s %s \"%s\"", command, playerName, reason));
    }

    public void pardon(String playerName)
    {
        final String command = "pardon";

        rconClient.sendRaw(String.format("%s %s", command, playerName));
    }

    private String getResponseString(Future<RconResponse> responseFuture)
    {
        try
        {
            return responseFuture.get(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS).getResponseString();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unable to complete RCON command execution", e);
        }
    }

    private class GameRuleWrapper
    {
        private final String command = "gamerule";

        public void setGameRule(GameRules gameRule, boolean value)
        {
            doSetGameRule(gameRule.getGameRuleName(), String.valueOf(value));
        }

        public void setGameRule(GameRules gameRule, int value)
        {
            doSetGameRule(gameRule.getGameRuleName(), String.valueOf(value));
        }

        public String getGameRule(GameRules gameRule)
        {
            final Future<RconResponse> responseFuture = rconClient.sendRaw(String.format("%s %s", command, gameRule));
            return getResponseString(responseFuture);
        }

        private void doSetGameRule(String gameRule, String value)
        {
            rconClient.sendRaw(String.format("%s %s %s", command, gameRule, value));
        }
    }
}
