package io.graversen.minecraft.rcon;

public class RconCommandException extends RuntimeException {
    public RconCommandException(String message, Object... args) {
        super(String.format(message, args));
    }

    public RconCommandException(Throwable cause, String message, Object... args) {
        super(String.format(message, args), cause);
    }
}
