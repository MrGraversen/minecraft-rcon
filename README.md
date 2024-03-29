# minecraft-rcon
[![](https://jitpack.io/v/MrGraversen/minecraft-rcon.svg)](https://jitpack.io/#MrGraversen/minecraft-rcon)

A MineCraft RCON client in Java.

## About
The client is made to eventually automate some administrative actions of a MineCraft server. Every MineCraft command is exposed over RCON, and made available using this client.

Some of the RCON commands are fairly complex in structure; this client will provide convenience methods and builders to help developers construct the correct commands to get the job done.

The entire RCON command libarary is *not* implemeneted, but will be updated along the way. Feel free to PR and contribute with implementations of missing commands.

This project will also provide some convenience code for working with the MineCraft target selectors (e.g. `@a` and so on), the 16 colors of the game, different labels (time of day, game rules, etc.) and other constants.

Using the convenience methods will synchronously access the RCON stack, but it is also possible to send raw, asynchronous RCON messages.

**Warning:** There's a long-standing issue with MineCraft RCON which, for some reason, causes RCON commands to execute from a not-thread-safe queue (whereas the normal MineCraft action queue execute from a thread-safe queue) (https://bugs.mojang.com/browse/MC-72390). Sometimes when executing many commands rapidly, e.g. giving a player *many* items, the game server will throw a `java.util.ConcurrentModificationException`, which sometimes casues it to crash entirely.  
I have, however, only seen this happening when I was really pushing it.

### What is RCON?
Originally invented by Valve for the Source Engine, it's a TCP/IP protocol for remote administration of game servers. The MineCraft RCON protocol is more-or-less an implementation of the Source Engine RCON protocol. It was introduced in beta update 1.9.

## Install

You may use JitPack to install this from the GitHub releases.  
Add the following to your `pom.xml` if using Maven (click the little JitPack badge for other build systems):

```
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

```
<dependency>
	<groupId>io.github.MrGraversen</groupId>
	<artifactId>minecraft-rcon</artifactId>
	<version>0.0.16</version>
</dependency>
```

## Minecraft Configuration

To configure Minecraft RCON, you need to add/modify the following properties in your Minecraft server's `server.properties` file:

```
enable-rcon=true
rcon.password=abc123
rcon.port=<1-65535> (default: 25575)
broadcast-rcon-to-ops=false
```

Once done, RCON is available using this client.

## Example 1

The example is also available in the `io.graversen.minecraft.rcon.examples.Example1.java` class.

```java
// Define a simple MinecraftRconService
// Assuming Minecraft server is running on localhost and password set to "test"
// If no port is specified, the default Minecraft RCON port will be used
final MinecraftRconService minecraftRconService = new MinecraftRconService(
	RconDetails.localhost("test"),
	ConnectOptions.defaults()
);

// Let's go!
minecraftRconService.connectBlocking(Duration.ofSeconds(3));

// After connecting, we can (crudely) fetch the underlying Minecraft RCON provider
final MinecraftRcon minecraftRcon = minecraftRconService.minecraftRcon().orElseThrow(IllegalStateException::new);

// Build a TellRaw command - first half of the desired message
final TellRawCommand tellRawCommand1 = new TellRawCommandBuilder()
	.targeting(Selectors.ALL_PLAYERS)
	.withText("It's dangerous to go alone - ")
	.withColor(Colors.GRAY)
	.italic()
	.build();


// Build another TellRaw command - other half of the message
final TellRawCommand tellRawCommand2 = new TellRawCommandBuilder()
	.targeting(Selectors.ALL_PLAYERS)
	.withText("Take this!")
	.withColor(Colors.DARK_AQUA)
	.italic()
	.build();

// We are able to transparently stitch together multiple 'tellraw' commands,
// combining their styles and texts into a composite viewing
final TellRawCompositeCommand tellRawCompositeCommand = new TellRawCompositeCommand(List.of(tellRawCommand1, tellRawCommand2));

// Let's also add a nice title to the players' screens
final TitleCommand titleCommand = new TitleCommandBuilder()
	.targeting(Selectors.ALL_PLAYERS)
	.atPosition(TitlePositions.TITLE)
	.withColor(Colors.GREEN)
	.withText("Welcome!")
	.build();

// We'll give everyone a diamond sword - it's dangerous without
final GiveCommand giveCommand = new GiveCommand(
	Selectors.ALL_PLAYERS.getSelectorString(), new MinecraftItem("diamond_sword"), 1
);

// Fire away!
minecraftRcon.sendAsync(tellRawCompositeCommand, titleCommand, giveCommand);

// Just for fun, let's also change some other things

// Set time of day to noon and clear weather - nice and sunny
final TimeCommand timeCommand = new TimeCommand(TimeLabels.NOON);
final WeatherCommand weatherCommand = new WeatherCommand(Weathers.CLEAR, Duration.ofHours(1).toSeconds());
minecraftRcon.sendAsync(timeCommand, weatherCommand);

// The players hate it when their creations are blown up by Creepers, lets' help them
final ICommand disableMobGriefing = GameRulesCommands.setGameRule(GameRules.MOB_GRIEFING, false);
minecraftRcon.sendAsync(disableMobGriefing);
```

**In-game result:**

![Result](https://i.imgur.com/JFowbeh.png)

**Sample console output:**

```
14:07:27.700 [pool-1-thread-2] DEBUG io.graversen.minecraft.rcon.service.ConnectTask - Connection attempt 1
14:07:27.718 [pool-1-thread-2] INFO io.graversen.minecraft.rcon.MinecraftClient - Initialized with connection tuple 'localhost:25575'
14:07:27.718 [pool-1-thread-2] DEBUG io.graversen.minecraft.rcon.MinecraftClient - Authenticating...
14:07:27.721 [pool-1-thread-2] INFO io.graversen.minecraft.rcon.MinecraftClient - Connection success!
14:07:27.770 [pool-2-thread-1] DEBUG io.graversen.minecraft.rcon.MinecraftClient - Sending command: tellraw @a [{"text":"It\u0027s dangerous to go alone - ","bold":false,"italic":true,"underlined":false,"striketrough":false,"obfuscated":false,"color":"gray"},{"text":"Take this!","bold":false,"italic":true,"underlined":false,"striketrough":false,"obfuscated":false,"color":"dark_aqua"}]
14:07:27.771 [pool-2-thread-1] DEBUG io.graversen.minecraft.rcon.MinecraftClient - Sending command: title @a title {"text":"Welcome!","bold":false,"italic":false,"underlined":false,"striketrough":false,"obfuscated":false,"color":"green"}
14:07:27.771 [pool-2-thread-1] DEBUG io.graversen.minecraft.rcon.MinecraftClient - Sending command: give @a minecraft:diamond_sword 1
14:07:27.782 [pool-2-thread-1] DEBUG io.graversen.minecraft.rcon.MinecraftClient - Sending command: time set 6000
14:07:27.782 [pool-2-thread-1] DEBUG io.graversen.minecraft.rcon.MinecraftClient - Sending command: weather clear 3600
14:07:27.798 [pool-2-thread-1] DEBUG io.graversen.minecraft.rcon.MinecraftClient - Sending command: gamerule mobGriefing false

```
