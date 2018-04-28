package io.graversen.minecraft.rcon;

import io.graversen.minecraft.rcon.commands.builders.TellRawCommandBuilder;
import io.graversen.minecraft.rcon.commands.objects.TellRawCommand;
import io.graversen.minecraft.rcon.util.Colors;
import io.graversen.minecraft.rcon.util.Selectors;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RconApp
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        final RconClient rconClient = RconClient.connect("localhost", "abc123");
        final Future<RconResponse> rconResponse = rconClient.sendRaw("/tellraw @a {\"keybind\":\"key.drop\"}\n");
        final RconResponse rconResponse1 = rconResponse.get();

        rconClient.rcon().tellRaw(new TellRawCommand(Selectors.ALL_PLAYERS.getSelectorString(), "Hallo dette er en test", Colors.DARK_PURPLE.getColorName(), false, true));
        System.out.println(rconClient.sendRaw("list").get().getResponseString());

        TellRawCommandBuilder tellRawCommandBuilder = new TellRawCommandBuilder();
        final TellRawCommand tellRawCommand = tellRawCommandBuilder.targeting("MrSkurk").withColor(Colors.RED).withText("Hej med dig fra TellRawCommandBuilder").build();
        rconClient.rcon().tellRaw(tellRawCommand);

        final List<String> playerList = rconClient.rcon().playerList();
    }
}
