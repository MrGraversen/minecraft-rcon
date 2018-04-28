package io.graversen.minecraft.rcon;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RconClient implements Closeable
{
    private static final int DEFAULT_PORT = 25575;

    private static final int RCON_AUTHENTICATION_FAILURE = -1;
    private static final int RCON_COMMAND = 2;
    private static final int RCON_AUTHENTICATION = 3;

    private final SocketChannel rconSocketChannel;
    private final AtomicInteger currentRequestCounter;
    private final ExecutorService executorService;

    private RconClient(SocketChannel rconSocketChannel)
    {
        this.rconSocketChannel = rconSocketChannel;
        this.currentRequestCounter = new AtomicInteger(1);
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public static RconClient connect(String hostname, String password)
    {
        return RconClient.connect(hostname, password, DEFAULT_PORT);
    }

    public static RconClient connect(String hostname, String password, int port)
    {
        try
        {
            final SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(hostname, port));
            final RconClient rconClient = new RconClient(socketChannel);

            final Future<RconResponse> authenticateResponse = rconClient.authenticateClient(password);
            final RconResponse rconResponse = authenticateResponse.get(5000, TimeUnit.MILLISECONDS);

            
        }
        catch (IOException | InterruptedException | ExecutionException e)
        {
            throw new RuntimeException(String.format("Connection to %s:%d failed", hostname, port), e);
        }
        catch (TimeoutException e)
        {
            throw new RuntimeException(String.format("Connection to %s:%d timed out", hostname, port), e);
        }
    }

    private Future<RconResponse> authenticateClient(String password)
    {
        return sendRaw(RCON_AUTHENTICATION, password);
    }

    public Future<RconResponse> sendRaw(String command)
    {
        return sendRaw(RCON_COMMAND, command);
    }

    private Future<RconResponse> sendRaw(int requestType, String command)
    {
        return executorService.submit(doSynchronousSend(currentRequestCounter.incrementAndGet(), requestType, command));
    }

    private Callable<RconResponse> doSynchronousSend(int requestCount, int requestType, String command)
    {
        return () -> {
            final long requestStart = System.currentTimeMillis();

            final long requestEnd = System.currentTimeMillis();
            return new RconResponse(requestStart, requestEnd, "Hello m8");
        };
    }

    private ByteBuffer createRconByteBuffer(int requestCount, int requestType, String command)
    {
        // In accordance with the RCON format: Length + Request ID + Type + Payload + Two nul bytes
        ByteBuffer byteBuffer = ByteBuffer.allocate((3 * Integer.BYTES) + command.length() + (2 * Byte.BYTES));
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

        byteBuffer.putInt((2 * Integer.BYTES) + command.length() + (2 * Byte.BYTES));
        byteBuffer.putInt(requestCount);
        byteBuffer.putInt(requestType);
        byteBuffer.put(command.getBytes());
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 0);

        byteBuffer.position(0);
        return byteBuffer;

    }

    @Override
    public void close() throws IOException
    {

    }
}
