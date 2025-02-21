package rs.inve;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MdpMessageTest {

    private final Gson gson = new Gson();

    @Test
    public void testInfoMessage() {
        Mdp mdp = new Mdp();
        mdp.setCat(Mdp.Cat.INFO);
        Value value = new Value();
        value.setInfoValue("You are connected to the RPi!");
        mdp.setValue(value);

        String json = gson.toJson(mdp);
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        assertEquals("info", jsonObject.get("cat").getAsString());
        assertEquals("You are connected to the RPi!", jsonObject.get("value").getAsJsonObject().get("infoValue").getAsString());
    }

    @Test
    public void testErrorMessage() {
        Mdp mdp = new Mdp();
        mdp.setCat(Mdp.Cat.ERROR);
        Value value = new Value();
        value.setErrorValue(Value.ErrorValue.API_IS_DOWN_START_COMMAND_ABORTED);
        mdp.setValue(value);

        String json = gson.toJson(mdp);
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        assertEquals("error", jsonObject.get("cat").getAsString());
        assertEquals("API is down, start command aborted.", jsonObject.get("value").getAsJsonObject().get("errorValue").getAsString());
    }

    @Test
    public void testLocationMessage() {
        Mdp mdp = new Mdp();
        mdp.setCat(Mdp.Cat.LOCATION);
        Value value = new Value();
        LocationValue locationValue = new LocationValue(1, 2, 3);
        value.setLocationValue(locationValue);
        mdp.setValue(value);

        String json = gson.toJson(mdp);
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        assertEquals("location", jsonObject.get("cat").getAsString());
        JsonObject locValue = jsonObject.get("value").getAsJsonObject().get("locationValue").getAsJsonObject();
        assertEquals(1, locValue.get("x").getAsInt());
        assertEquals(2, locValue.get("y").getAsInt());
        assertEquals(3, locValue.get("d").getAsInt());
    }

    @Test
    public void testImageRecMessage() {
        Mdp mdp = new Mdp();
        mdp.setCat(Mdp.Cat.IMAGE_REC);
        Value value = new Value();
        ImageRecValue imageRecValue = new ImageRecValue("A", "1");
        value.setImageRecValue(imageRecValue);
        mdp.setValue(value);

        String json = gson.toJson(mdp);
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        assertEquals("image-rec", jsonObject.get("cat").getAsString());
        JsonObject imgRecValue = jsonObject.get("value").getAsJsonObject().get("imageRecValue").getAsJsonObject();
        assertEquals("A", imgRecValue.get("image_id").getAsString());
        assertEquals("1", imgRecValue.get("obstacle_id").getAsString());
    }

    @Test
    public void testStatusMessage() {
        Mdp mdp = new Mdp();
        mdp.setCat(Mdp.Cat.STATUS);
        Value value = new Value();
        value.setStatusValue(Value.StatusValue.RUNNING);
        mdp.setValue(value);

        String json = gson.toJson(mdp);
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        assertEquals("status", jsonObject.get("cat").getAsString());
        assertEquals("running", jsonObject.get("value").getAsJsonObject().get("statusValue").getAsString());
    }

    @Test
    public void testObstaclesMessage() {
        Mdp mdp = new Mdp();
        mdp.setCat(Mdp.Cat.OBSTACLES);
        Value value = new Value();
        ObstaclesValue obstaclesValue = new ObstaclesValue();
        obstaclesValue.setObstacles(Arrays.asList(
                new Obstacle(5, 10, 1, 2),
                new Obstacle(10, 20, 2, 1)
        ));
        obstaclesValue.setMode("0");
        value.setObstaclesValue(obstaclesValue);
        mdp.setValue(value);

        String json = gson.toJson(mdp);
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        assertEquals("obstacles", jsonObject.get("cat").getAsString());
        JsonObject obsValue = jsonObject.get("value").getAsJsonObject().get("obstaclesValue").getAsJsonObject();
        assertEquals(2, obsValue.get("obstacles").getAsJsonArray().size());
        assertEquals("0", obsValue.get("mode").getAsString());
    }

    @Test
    public void testControlMessage() {
        Mdp mdp = new Mdp();
        mdp.setCat(Mdp.Cat.CONTROL);
        Value value = new Value();
        value.setControlValue("FW090");
        mdp.setValue(value);

        String json = gson.toJson(mdp);
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        assertEquals("control", jsonObject.get("cat").getAsString());
        assertEquals("FW090", jsonObject.get("value").getAsJsonObject().get("controlValue").getAsString());
    }

    @Test
    public void testDeserialization() {
        String json = "{\"cat\":\"location\",\"value\":{\"locationValue\":{\"x\":1,\"y\":2,\"d\":3}}}";
        Mdp mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.LOCATION, mdp.getCat());
        LocationValue locationValue = mdp.getValue().getLocationValue();
        assertNotNull(locationValue);
        assertEquals(1, locationValue.getX());
        assertEquals(2, locationValue.getY());
        assertEquals(3, locationValue.getD());
    }

    @Test
    public void testInfoMessageDeserialization() {
        String json = "{\"cat\":\"info\",\"value\":{\"infoValue\":\"You are connected to the RPi!\"}}";
        Mdp mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.INFO, mdp.getCat());
        assertEquals("You are connected to the RPi!", mdp.getValue().getInfoValue());

        json = "{\"cat\":\"info\",\"value\":{\"infoValue\":{\"capturing_image\":{\"obstacle_id\":123}}}}";
        mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.INFO, mdp.getCat());
        JsonObject infoValue = gson.toJsonTree(mdp.getValue().getInfoValue()).getAsJsonObject();
        assertEquals(123, infoValue.getAsJsonObject("capturing_image").get("obstacle_id").getAsInt());
    }

    @Test
    public void testErrorMessageDeserialization() {
        String json = "{\"cat\":\"error\",\"value\":{\"errorValue\":\"API is down, start command aborted.\"}}";
        Mdp mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.ERROR, mdp.getCat());
        assertEquals(Value.ErrorValue.API_IS_DOWN_START_COMMAND_ABORTED, mdp.getValue().getErrorValue());
    }

    @Test
    public void testLocationMessageDeserialization() {
        String json = "{\"cat\":\"location\",\"value\":{\"locationValue\":{\"x\":1,\"y\":2,\"d\":3}}}";
        Mdp mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.LOCATION, mdp.getCat());
        LocationValue loc = mdp.getValue().getLocationValue();
        assertNotNull(loc);
        assertEquals(1, loc.getX());
        assertEquals(2, loc.getY());
        assertEquals(3, loc.getD());
    }

    @Test
    public void testImageRecognitionMessageDeserialization() {
        String json = "{\"cat\":\"image-rec\",\"value\":{\"imageRecValue\":{\"image_id\":\"A\",\"obstacle_id\":\"1\"}}}";
        Mdp mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.IMAGE_REC, mdp.getCat());
        ImageRecValue rec = mdp.getValue().getImageRecValue();
        assertNotNull(rec);
        assertEquals("A", rec.getImageId());
        assertEquals("1", rec.getObstacleId());
    }

    @Test
    public void testStatusMessageDeserialization() {
        String json = "{\"cat\":\"status\",\"value\":{\"statusValue\":\"running\"}}";
        Mdp mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.STATUS, mdp.getCat());
        assertEquals(Value.StatusValue.RUNNING, mdp.getValue().getStatusValue());
    }

    @Test
    public void testObstaclesMessageDeserialization() {
        String json = "{\"cat\":\"obstacles\",\"value\":{\"obstaclesValue\":{\"obstacles\":[{\"x\":5,\"y\":10,\"id\":1,\"d\":2},{\"x\":10,\"y\":20,\"id\":2,\"d\":1}],\"mode\":\"0\"}}}";
        Mdp mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.OBSTACLES, mdp.getCat());
        ObstaclesValue obstaclesValue = mdp.getValue().getObstaclesValue();
        assertNotNull(obstaclesValue);
        List<Obstacle> obstacles = obstaclesValue.getObstacles();
        assertEquals(2, obstacles.size());
        assertEquals(5, obstacles.get(0).getX());
        assertEquals(10, obstacles.get(0).getY());
        assertEquals(1, obstacles.get(0).getId());
        assertEquals(2, obstacles.get(0).getD());
        assertEquals(10, obstacles.get(1).getX());
        assertEquals(20, obstacles.get(1).getY());
        assertEquals(2, obstacles.get(1).getId());
        assertEquals(1, obstacles.get(1).getD());
        assertEquals("0", obstaclesValue.getMode());
    }

    @Test
    public void testControlMessageDeserialization() {
        String json = "{\"cat\":\"control\",\"value\":{\"controlValue\":\"start\"}}";
        Mdp mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.CONTROL, mdp.getCat());
        assertEquals("start", mdp.getValue().getControlValue());

        json = "{\"cat\":\"control\",\"value\":{\"controlValue\":\"FW090\"}}";
        mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.CONTROL, mdp.getCat());
        assertEquals("FW090", mdp.getValue().getControlValue());

        json = "{\"cat\":\"control\",\"value\":{\"controlValue\":\"BW085\"}}";
        mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.CONTROL, mdp.getCat());
        assertEquals("BW085", mdp.getValue().getControlValue());

        json = "{\"cat\":\"control\",\"value\":{\"controlValue\":\"SNAP1_L\"}}";
        mdp = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.CONTROL, mdp.getCat());
        assertEquals("SNAP1_L", mdp.getValue().getControlValue());
    }

    @Test
    public void testInvalidMessageHandling() {
        String json = "{\"cat\":\"invalid\",\"value\":\"test\"}";
        assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, Mdp.class));

        String json2 = "{\"cat\":\"info\",\"value\":{\"infoValue\":\"invalid_info\"}}";
        Mdp mdp = gson.fromJson(json2, Mdp.class);
        assertEquals(Mdp.Cat.INFO, mdp.getCat());
        assertEquals("invalid_info", mdp.getValue().getInfoValue());
    }

    @Test
    public void testMessageRoundtrip() {
        String[] jsons = {
                "{\"cat\":\"info\",\"value\":{\"infoValue\":\"You are connected to the RPi!\"}}",
                "{\"cat\":\"info\",\"value\":{\"infoValue\":\"Robot is ready!\"}}",
                "{\"cat\":\"info\",\"value\":{\"infoValue\":{\"capturing_image\":{\"obstacle_id\":123}}}}",
                "{\"cat\":\"error\",\"value\":{\"errorValue\":\"API is down, start command aborted.\"}}",
                "{\"cat\":\"location\",\"value\":{\"locationValue\":{\"x\":1,\"y\":2,\"d\":3}}}"
        };

        for (String originalJson : jsons) {
            Mdp mdp = gson.fromJson(originalJson, Mdp.class);
            String serializedJson = gson.toJson(mdp);
            JsonObject original = gson.fromJson(originalJson, JsonObject.class);
            JsonObject serialized = gson.fromJson(serializedJson, JsonObject.class);
            assertEquals(original, serialized);
        }
    }
}
