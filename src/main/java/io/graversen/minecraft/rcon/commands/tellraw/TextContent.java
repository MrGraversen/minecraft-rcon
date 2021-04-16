package io.graversen.minecraft.rcon.commands.tellraw;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TextContent {
    private final String text;
    private final boolean bold;
    private final boolean italic;
    private final boolean underlined;
    private final boolean striketrough;
    private final boolean obfuscated;
    private final String color;

    public TextContent(TellRawCommand tellRawCommand) {
        this.text = tellRawCommand.getText();
        this.bold = tellRawCommand.isBold();
        this.italic = tellRawCommand.isItalic();
        this.underlined = tellRawCommand.isUnderlined();
        this.striketrough = tellRawCommand.isStriketrough();
        this.obfuscated = tellRawCommand.isObfuscated();
        this.color = tellRawCommand.getColor();
    }
}
