package com.github.zeon256;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ParameterizedControlValue {
    private final String command;
    private final Integer parameter;

    public ParameterizedControlValue(String command, Integer parameter) {
        this.command = command;
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        if (parameter != null) {
            return String.format("%s%03d", command, parameter);
        }
        return command;
    }

    public static ParameterizedControlValue fromString(String text) {
        if (text.equals("start")) {
            return new ParameterizedControlValue("start", null);
        }
        if (text.startsWith("SNAP")) {
            return new ParameterizedControlValue(text, null);
        }
        if (text.length() == 5 && (text.startsWith("FW") || text.startsWith("BW") ||
                text.startsWith("TL") || text.startsWith("TR") || text.startsWith("BL") || text.startsWith("BR"))) {
            String command = text.substring(0, 2);
            int parameter = Integer.parseInt(text.substring(2));
            return new ParameterizedControlValue(command, parameter);
        }
        return null;
    }

    public static class Serializer implements JsonSerializer<ParameterizedControlValue>, JsonDeserializer<ParameterizedControlValue> {
        @Override
        public JsonElement serialize(ParameterizedControlValue src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }

        @Override
        public ParameterizedControlValue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return ParameterizedControlValue.fromString(json.getAsString());
        }
    }
}
