# minecraft-rcon
[![](https://jitpack.io/v/MrGraversen/minecraft-rcon.svg)](https://jitpack.io/#MrGraversen/minecraft-rcon)

A MineCraft RCON client in Java.

## About
The client is made to eventually automate some administrative actions of a MineCraft server. Every MineCraft command is exposed over RCON, and made available using this client.

Some of the RCON commands are fairly complex in structure; this client will provide convenience methods and builders to help developers construct the correct commands to get the job done.

The entire RCON command libarary is *not* implemeneted, but will be updated along the way. Feel free to PR and contribute with implementations of missing commands.

This project will also provide some convenience code for working with the MineCraft target selectors (e.g. `@a` and so on), the 16 colors of the game, different labels (time of day, game rules, etc.) and other constants.

Using the convenience methods will synchronously access the RCON stack, but it is also possible to send raw, asynchronous RCON messages.

**Warning:** There's a long-standing issue with MineCraft RCON which, for some reason, causes RCON commands to execute from a not-thread-safe queue (whereas the normal MineCraft action queue execute from a thread-safe queue). Sometimes when executing many commands rapidly, e.g. giving a player *many* items, the game server will throw a `java.util.ConcurrentModificationException`, which sometimes casues it to crash entirely.  
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
	<groupId>com.github.MrGraversen</groupId>
	<artifactId>minecraft-rcon</artifactId>
	<version>LATEST</version>
</dependency>
```

## Configuration

To configure RCON, you need to add/modify the following properties in your `server.properties` file:

```
enable-rcon=true
rcon.password=abc123
rcon.port=<1-65535> (default: 25575)
broadcast-rcon-to-ops=false
```

Once done, RCON is available using this client.

## Example

The example is also available in the `io.graversen.minecraft.rcon.examples.TellRawAndGiveExample` class.

```java
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
```

**Result:**

![Result](https://i.imgur.com/JFowbeh.png)

**Sample console output:**

```
[RconClient]: Initialized: localhost:25575
[RconClient]: Authenticating
[RconClient]: Piping command: tellraw @a [{"text":"It\u0027s dangerous to go alone - ","bold":false,"italic":true,"underlined":false,"striketrough":false,"obfuscated":false,"color":"gray"},{"text":"Take this!","bold":false,"italic":true,"underlined":false,"striketrough":false,"obfuscated":false,"color":"dark_aqua"}]
[RconClient]: Piping command: title @a title {"text":"Welcome!","bold":false,"italic":false,"underlined":false,"striketrough":false,"obfuscated":false,"color":"green"}
[RconClient]: Piping command: give @a minecraft:diamond_sword 1
[RconClient]: Piping command: gamerule mobGriefing false
[RconClient]: Piping command: time set day
```
