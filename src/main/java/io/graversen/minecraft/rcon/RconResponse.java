package io.graversen.minecraft.rcon;

public class RconResponse {
    private final long requestStart;
    private final long requestEnd;
    private final long requestDuration;
    private final int requestCounter;
    private final int responseId;
    private final String responseString;

    public RconResponse(long requestStart, long requestEnd, int requestCounter, int responseId, String responseString) {
        this.requestStart = requestStart;
        this.requestEnd = requestEnd;
        this.requestCounter = requestCounter;
        this.responseId = responseId;
        this.requestDuration = requestEnd - requestStart;
        this.responseString = responseString;
    }

    public long getRequestStart() {
        return requestStart;
    }

    public long getRequestEnd() {
        return requestEnd;
    }

    public long getRequestDuration() {
        return requestDuration;
    }

    public String getResponseString() {
        return responseString;
    }

    public int getRequestCounter() {
        return requestCounter;
    }

    public int getResponseId() {
        return responseId;
    }
}
