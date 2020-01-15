package io.graversen.minecraft.rcon.examples;

import io.graversen.minecraft.rcon.MinecraftClient;
import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.commands.tellraw.TellRawCommandBuilder;
import io.graversen.minecraft.rcon.commands.tellraw.TellRawCompositeCommand;
import io.graversen.minecraft.rcon.util.Colors;
import io.graversen.minecraft.rcon.util.HoverEventActions;
import io.graversen.minecraft.rcon.util.Selectors;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        final var minecraftClient = MinecraftClient.connect("78.47.91.199", "ecb42ecb-1254-4a66-a0b0-5fd75d9df127");
        final var minecraftRcon = new MinecraftRcon(minecraftClient);

        final var sender = new TellRawCommandBuilder()
                .targeting(Selectors.ALL_PLAYERS)
                .withColor(Colors.LIGHT_PURPLE)
                .bold()
                .withText("[Ownzone]: ")
                .build();

        final var message = new TellRawCommandBuilder()
                .targeting(Selectors.ALL_PLAYERS)
                .withColor(Colors.WHITE)
                .withText("Latest version of Ownzone Minecraft is deployed.")
                .build();

        minecraftRcon.sendAsync(new TellRawCompositeCommand(List.of(sender, message)));

        final var hoverTest = new TellRawCommandBuilder()
                .targeting(Selectors.ALL_PLAYERS)
                .withText("Test")
                .obfuscated()
                .withHoverEvent(HoverEventActions.SHOW_TEXT, "test Hover")
                .build();

        minecraftRcon.sendAsync(hoverTest);
    }
}
