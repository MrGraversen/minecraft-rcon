package examples;

import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.RconResponse;
import io.graversen.minecraft.rcon.commands.base.ICommand;
import io.graversen.minecraft.rcon.service.ConnectOptions;
import io.graversen.minecraft.rcon.service.MinecraftRconService;
import io.graversen.minecraft.rcon.service.RconDetails;

record CustomCommand(String command) implements ICommand {}

public class Example2 {

    public static void main(String[] args) throws InterruptedException {
        MinecraftRconService connection = new MinecraftRconService(
                new RconDetails("192.168.x.x", 25575, "password"),
                ConnectOptions.defaults()
        );
        connection.connect();

        while (!connection.isConnected()) {
            Thread.sleep(100);
        }

        MinecraftRcon rcon = connection.minecraftRcon().orElseThrow();

        CustomCommand command = new CustomCommand("version");
        RconResponse response = rcon.sendSync(command);

        System.out.println(response.getResponseString().replaceAll("§.", ""));

        connection.disconnect();
    }
}
