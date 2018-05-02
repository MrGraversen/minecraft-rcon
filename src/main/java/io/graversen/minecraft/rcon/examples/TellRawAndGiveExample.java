package io.graversen.minecraft.rcon.examples;

import io.graversen.minecraft.rcon.RconClient;
import io.graversen.minecraft.rcon.commands.builders.GiveCommandBuilder;
import io.graversen.minecraft.rcon.commands.builders.TellRawCommandBuilder;
import io.graversen.minecraft.rcon.commands.builders.TitleCommandBuilder;
import io.graversen.minecraft.rcon.commands.objects.GiveCommand;
import io.graversen.minecraft.rcon.commands.objects.TellRawCommand;
import io.graversen.minecraft.rcon.commands.objects.TitleCommand;
import io.graversen.minecraft.rcon.util.*;

public class TellRawAndGiveExample
{
    public static void main(String[] args)
    {
        // Prepare the builders
        final TellRawCommandBuilder tellRawCommandBuilder = new TellRawCommandBuilder();
        final TitleCommandBuilder titleCommandBuilder = new TitleCommandBuilder();
        final GiveCommandBuilder giveCommandBuilder = new GiveCommandBuilder();

        // Attempt to open and authenticate a RCON connection
        // We assume the server is running locally, and the RCON password is "abc123"
        // If no port is specified, it will use the default RCON port
        final RconClient rconClient = RconClient.connect("localhost", "abc123");

        // Build a TellRaw command
        final TellRawCommand tellRawCommand1 = tellRawCommandBuilder
                .targeting(Selectors.ALL_PLAYERS)
                .withText("It's dangerous to go alone - ")
                .withColor(Colors.GRAY)
                .italic()
                .build();

        // Build another TellRaw command
        // The RCON client is able to transparently string together multiple TellRaw commands,
        // if for example different colors and formatting is desired for a single message
        final TellRawCommand tellRawCommand2 = tellRawCommandBuilder
                .targeting(Selectors.ALL_PLAYERS)
                .withText("Take this!")
                .withColor(Colors.DARK_AQUA)
                .italic()
                .build();

        // Let's also add a nice title to the players' screens
        final TitleCommand titleCommand = titleCommandBuilder
                .targeting(Selectors.ALL_PLAYERS)
                .atPosition(TitlePositions.TITLE)
                .withColor(Colors.GREEN)
                .withText("Welcome!")
                .build();

        // We'll give everyone a diamond sword - it's dangerous without
        final GiveCommand giveCommand = giveCommandBuilder
                .targeting(Selectors.ALL_PLAYERS)
                .withItem("minecraft", "diamond_sword")
                .amount(1)
                .build();

        // Send the two TellRaw commands
        rconClient.rcon().tellRaw(tellRawCommand1, tellRawCommand2);

        // Set the title
        rconClient.rcon().title(titleCommand);

        // Grant the players their weapon
        rconClient.rcon().give(giveCommand);

        // It is also easy to change game rules of the server
        rconClient.rcon().gameRules().setGameRule(GameRules.MOB_GRIEFING, false);

        // Changing time of day is trivial as well
        rconClient.rcon().time(TimeLabels.DAY);
    }
}
