package io.graversen.minecraft.rcon.commands.tellraw;


import java.util.Arrays;
import java.util.Objects;

public record HoverEvent(String action, TextContent[] contents) {

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (HoverEvent) obj;
        return Objects.equals(this.action, that.action) &&
                Arrays.equals(this.contents, that.contents);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(action);
        result = 31 * result + Arrays.hashCode(contents);
        return result;
    }

    @Override
    public String toString() {
        return "HoverEvent[" +
                "action=" + action + ", " +
                "contents=" + Arrays.toString(contents) + ']';
    }
}
