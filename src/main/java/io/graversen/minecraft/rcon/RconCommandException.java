package io.graversen.minecraft.rcon;

public class RconCommandException extends RuntimeException {
    public RconCommandException(Throwable cause, String message, Object... args) {
        super(String.format(message, args), cause);
    }
}
