package io.graversen.minecraft.rcon.util;

public class Experience {
    private final int amount;
    private final String type;

    private Experience(int amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public static Experience points(int amount) {
        return new Experience(amount, "points");
    }

    public static Experience levels(int amount) {
        return new Experience(amount, "levels");
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%d %s", amount, type);
    }
}
