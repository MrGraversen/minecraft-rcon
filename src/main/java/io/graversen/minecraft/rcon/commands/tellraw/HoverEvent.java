package io.graversen.minecraft.rcon.commands.tellraw;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class HoverEvent {
    private final String action;
    private final TextContent[] contents;
}
