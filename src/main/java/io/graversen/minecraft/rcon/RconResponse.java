package io.graversen.minecraft.rcon;

public class RconResponse
{
    private final long requestStart;
    private final long requestEnd;
    private final long requestDuration;
    private final String responseString;

    public RconResponse(long requestStart, long requestEnd, String responseString)
    {
        this.requestStart = requestStart;
        this.requestEnd = requestEnd;
        this.requestDuration = requestEnd - requestStart;
        this.responseString = responseString;
    }

    public long getRequestStart()
    {
        return requestStart;
    }

    public long getRequestEnd()
    {
        return requestEnd;
    }

    public long getRequestDuration()
    {
        return requestDuration;
    }

    public String getResponseString()
    {
        return responseString;
    }
}
