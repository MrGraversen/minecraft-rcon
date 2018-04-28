package io.graversen.minecraft.rcon;

import com.google.gson.Gson;
import io.graversen.minecraft.rcon.commands.objects.EffectCommand;
import io.graversen.minecraft.rcon.commands.objects.TellRawCommand;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Rcon
{
    private final static int DEFAULT_TIMEOUT = 5000;

    private final Gson gson;
    private final IRconClient rconClient;

    public Rcon(IRconClient rconClient)
    {
        this.gson = new Gson();
        this.rconClient = rconClient;
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
        final String[] players = responseString.split(":")[1].split(",");

        return Arrays.asList(players);
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
}
