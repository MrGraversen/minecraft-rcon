package io.graversen.minecraft.rcon;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RconApp
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        final RconClient rconClient = RconClient.connect("localhost", "abc123");
        final Future<RconResponse> rconResponse = rconClient.sendRaw("lol");
        final RconResponse rconResponse1 = rconResponse.get();
        System.out.println(rconResponse1.getResponseString());
    }
}
