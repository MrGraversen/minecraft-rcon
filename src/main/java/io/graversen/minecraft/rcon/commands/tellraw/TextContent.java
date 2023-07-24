package io.graversen.minecraft.rcon.commands.tellraw;

public record TextContent(
        String text,
        boolean bold,
        boolean italic,
        boolean underlined,
        boolean strikethrough,
        boolean obfuscated,
        String color
) {

    public TextContent(TellRawCommand tellRawCommand) {
        this(
            tellRawCommand.getText(),
            tellRawCommand.isBold(),
            tellRawCommand.isItalic(),
            tellRawCommand.isUnderlined(),
            tellRawCommand.isStrikethrough(),
            tellRawCommand.isObfuscated(),
            tellRawCommand.getColor()
        );
    }
}
