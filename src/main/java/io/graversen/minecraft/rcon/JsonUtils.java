package io.graversen.minecraft.rcon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
    private static final Gson GSON_INSTANCE = new GsonBuilder().disableHtmlEscaping().create();

    public static String toJson(Object object) {
        return GSON_INSTANCE.toJson(object);
    }
}
