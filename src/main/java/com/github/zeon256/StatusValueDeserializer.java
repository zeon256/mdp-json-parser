package com.github.zeon256;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class StatusValueDeserializer implements JsonDeserializer<StatusValue> {
    @Override
    public StatusValue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String value = json.getAsString();
        return StatusValue.fromValue(value);
    }
}

