package com.github.zeon256;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class InfoValueDeserializer implements JsonDeserializer<InfoValue> {
    @Override
    public InfoValue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String value = json.getAsString();
        for (InfoValue infoValue : InfoValue.values()) {
            if (infoValue.toString().equals(value)) {
                return infoValue;
            }
        }
        throw new JsonParseException("Unknown InfoValue: " + value);
    }
}
