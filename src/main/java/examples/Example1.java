package examples;

import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.commands.GameRulesCommands;
import io.graversen.minecraft.rcon.commands.GiveCommand;
import io.graversen.minecraft.rcon.commands.TimeCommand;
import io.graversen.minecraft.rcon.commands.WeatherCommand;
import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.commands.tellraw.TellRawCommand;
import io.graversen.minecraft.rcon.commands.tellraw.TellRawCommandBuilder;
import io.graversen.minecraft.rcon.commands.tellraw.TellRawCompositeCommand;
import io.graversen.minecraft.rcon.commands.title.TitleCommand;
import io.graversen.minecraft.rcon.commands.title.TitleCommandBuilder;
import io.graversen.minecraft.rcon.service.ConnectOptions;
import io.graversen.minecraft.rcon.service.MinecraftRconService;
import io.graversen.minecraft.rcon.service.RconDetails;
import io.graversen.minecraft.rcon.util.*;

import java.time.Duration;
import java.util.List;

public class Example1 {

    public static void main(String[] args) {

        // Define a simple MinecraftRconService
        // Assuming Minecraft server is running on localhost and password set to "test"
        // If no port is specified, the default Minecraft RCON port will be used.
        final MinecraftRconService minecraftRconService = new MinecraftRconService(
                RconDetails.localhost("test"),
                ConnectOptions.defaults()
        );

        // Let's go!
        minecraftRconService.connectBlocking(Duration.ofSeconds(3));

        // After connecting, we can (crudely) fetch the underlying Minecraft RCON provider.
        final MinecraftRcon minecraftRcon = minecraftRconService.minecraftRcon().orElseThrow(IllegalStateException::new);

        // Build a TellRaw command – first half of the desired message
        final TellRawCommand tellRawCommand1 = new TellRawCommandBuilder()
                .targeting(Selector.ALL_PLAYERS)
                .withText("It's dangerous to go alone - ")
                .withColor(Color.GRAY)
                .italic()
                .build();


        // Build another TellRaw command – other half of the message
        final TellRawCommand tellRawCommand2 = new TellRawCommandBuilder()
                .targeting(Selector.ALL_PLAYERS)
                .withText("Take this!")
                .withColor(Color.DARK_AQUA)
                .italic()
                .build();

        // We can transparently stitch together multiple 'tellraw' commands,
        // combining their styles and texts into a composite viewing.
        final TellRawCompositeCommand tellRawCompositeCommand = new TellRawCompositeCommand(List.of(tellRawCommand1, tellRawCommand2));

        // Let's also add a nice title to the players' screens.
        final TitleCommand titleCommand = new TitleCommandBuilder()
                .targeting(Selector.ALL_PLAYERS)
                .atPosition(TitlePosition.TITLE)
                .withColor(Color.GREEN)
                .withText("Welcome!")
                .build();

        // We'll give everyone a diamond sword – it is dangerous without.
        final GiveCommand giveCommand = new GiveCommand(
                Target.selector(Selector.ALL_PLAYERS), new MinecraftItem("diamond_sword"), null, 1
        );

        // Fire away!
        minecraftRcon.sendAsync(tellRawCompositeCommand, titleCommand, giveCommand);

        // Just for fun, let's also change some other things

        // Set time of day to noon and clear weather – nice and sunny.
        final TimeCommand timeCommand = new TimeCommand(TimeLabel.NOON);
        final WeatherCommand weatherCommand = new WeatherCommand(Weather.CLEAR, Duration.ofHours(1).toSeconds());
        minecraftRcon.sendAsync(timeCommand, weatherCommand);

        // The players hate it when their creations are blown up by Creepers, lets' help them.
        final ICommand disableMobGriefing = GameRulesCommands.setGameRule(GameRule.MOB_GRIEFING, false);
        minecraftRcon.sendAsync(disableMobGriefing);
    }
}
