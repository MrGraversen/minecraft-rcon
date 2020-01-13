package io.graversen.minecraft.rcon;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Invalid RCON password");
    }
}
