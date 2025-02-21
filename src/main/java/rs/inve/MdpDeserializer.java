package rs.inve;

import com.google.gson.*;
import java.lang.reflect.Type;

import com.google.gson.*;
import java.lang.reflect.Type;

public class MdpDeserializer implements JsonDeserializer<Mdp<?>> {
    @Override
    public Mdp<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Mdp.Cat cat = Mdp.Cat.fromValue(jsonObject.get("cat").getAsString());
        JsonElement value = jsonObject.get("value");

        Object deserializedValue = switch (cat) {
            case INFO -> context.deserialize(value, InfoValue.class);
            case ERROR -> context.deserialize(value, ErrorValue.class);
            case STATUS -> context.deserialize(value, StatusValue.class);
            case LOCATION -> context.deserialize(value, LocationValue.class);
            case OBSTACLES -> context.deserialize(value, ObstaclesValue.class);
            case IMAGE_REC -> context.deserialize(value, ImageRecValue.class);
            case CONTROL -> {
                if (value.isJsonPrimitive()) {
                    yield value.getAsString();
                } else {
                    yield context.deserialize(value, ParameterizedControlValue.class);
                }
            }
            default -> throw new JsonParseException("Unknown category: " + cat);
        };

        return new Mdp<>(cat, deserializedValue);
    }
}



