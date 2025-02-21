package rs.inve;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class MdpMessageTest {

    private Gson gson;

    @BeforeEach
    void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Mdp.class, new MdpDeserializer())
                .registerTypeAdapter(InfoValue.class, new InfoValueDeserializer())
                .registerTypeAdapter(ErrorValue.class, new ErrorValueDeserializer())
                .registerTypeAdapter(StatusValue.class, new StatusValueDeserializer())
                .registerTypeAdapter(ParameterizedControlValue.class, new ParameterizedControlValue.Serializer())
                .create();
    }

    @Test
    void testInfoMessageSerialization() {
        Mdp<InfoValue> infoMessage = new Mdp<>(Mdp.Cat.INFO, InfoValue.CONNECTED);
        String json = gson.toJson(infoMessage);
        assertEquals("{\"cat\":\"info\",\"value\":\"You are connected to the RPi!\"}", json);
    }

    @Test
    void testInfoMessageDeserialization() {
        String[] infoJsonSamples = {
                "{\"cat\":\"info\",\"value\":\"You are connected to the RPi!\"}",
                "{\"cat\":\"info\",\"value\":\"Robot is ready!\"}",
                "{\"cat\":\"info\",\"value\":\"You are reconnected!\"}",
                "{\"cat\":\"info\",\"value\":\"Starting robot on path!\"}",
                "{\"cat\":\"info\",\"value\":\"Commands queue finished!\"}",
                "{\"cat\":\"info\",\"value\":\"Requesting path from algo...\"}",
                "{\"cat\":\"info\",\"value\":\"Commands and path received Algo API. Robot is ready to move\"}",
                "{\"cat\":\"info\",\"value\":\"Images stitched!\"}"
        };

        for (String json : infoJsonSamples) {
            Mdp<?> infoMessage = gson.fromJson(json, Mdp.class);
            assertEquals(Mdp.Cat.INFO, infoMessage.getCat());
            assertInstanceOf(InfoValue.class, infoMessage.getValue());
        }
    }

    @Test
    void testErrorMessageSerialization() {
        Mdp<ErrorValue> errorMessage = new Mdp<>(Mdp.Cat.ERROR, ErrorValue.API_DOWN);
        String json = gson.toJson(errorMessage);
        assertEquals("{\"cat\":\"error\",\"value\":\"API is down, start command aborted.\"}", json);
    }

    @Test
    void testErrorMessageDeserialization() {
        String[] errorJsonSamples = {
                "{\"cat\":\"error\",\"value\":\"API is down, start command aborted.\"}",
                "{\"cat\":\"error\",\"value\":\"Command queue is empty, did you set obstacles?\"}",
                "{\"cat\":\"error\",\"value\":\"Something went wrong when requesting stitch from the API.\"}",
                "{\"cat\":\"error\",\"value\":\"Something went wrong when requesting path from Algo API.\"}"
        };

        for (String json : errorJsonSamples) {
            Mdp<?> errorMessage = gson.fromJson(json, Mdp.class);
            assertEquals(Mdp.Cat.ERROR, errorMessage.getCat());
            assertInstanceOf(ErrorValue.class, errorMessage.getValue());
        }
    }

    @Test
    void testLocationMessageSerialization() {
        LocationValue location = new LocationValue(1, 2, 3);
        Mdp<LocationValue> locationMessage = new Mdp<>(Mdp.Cat.LOCATION, location);
        String json = gson.toJson(locationMessage);
        assertEquals("{\"cat\":\"location\",\"value\":{\"x\":1,\"y\":2,\"d\":3}}", json);
    }

    @Test
    void testLocationMessageDeserialization() {
        String json = "{\"cat\":\"location\",\"value\":{\"x\":1,\"y\":2,\"d\":3}}";
        Mdp<?> locationMessage = gson.fromJson(json, Mdp.class);
        assertEquals(Mdp.Cat.LOCATION, locationMessage.getCat());
        assertInstanceOf(LocationValue.class, locationMessage.getValue());
        LocationValue location = (LocationValue) locationMessage.getValue();
        assertEquals(1, location.getX());
        assertEquals(2, location.getY());
        assertEquals(3, location.getD());
    }

    @Test
    void testImageRecognitionMessageSerialization() {
        ImageRecValue imageRec = new ImageRecValue("A", "1");
        Mdp<ImageRecValue> imageRecMessage = new Mdp<>(Mdp.Cat.IMAGE_REC, imageRec);
        String json = gson.toJson(imageRecMessage);
        assertEquals("{\"cat\":\"image-rec\",\"value\":{\"image_id\":\"A\",\"obstacle_id\":\"1\"}}", json);
    }

    @Test
    void testImageRecognitionMessageDeserialization() {
        String json = "{\"cat\":\"image-rec\",\"value\":{\"image_id\":\"A\",\"obstacle_id\":\"1\"}}";
        Mdp<?> imageRecMessage = gson.fromJson(json, Mdp.class);
        assertEquals(Mdp.Cat.IMAGE_REC, imageRecMessage.getCat());
        assertInstanceOf(ImageRecValue.class, imageRecMessage.getValue());
        ImageRecValue imageRec = (ImageRecValue) imageRecMessage.getValue();
        assertEquals("A", imageRec.getImageId());
        assertEquals("1", imageRec.getObstacleId());
    }

    @Test
    void testStatusMessageSerialization() {
        Mdp<StatusValue> statusMessage = new Mdp<>(Mdp.Cat.STATUS, StatusValue.RUNNING);
        String json = gson.toJson(statusMessage);
        assertEquals("{\"cat\":\"status\",\"value\":\"running\"}", json);
    }

    @Test
    void testStatusMessageDeserialization() {
        String[] statusJsonSamples = {
                "{\"cat\":\"status\",\"value\":\"running\"}",
                "{\"cat\":\"status\",\"value\":\"finished\"}"
        };

        String[] correctStatuses = {"running", "finished"};

        for (int i = 0; i < statusJsonSamples.length; i++) {
            String json = statusJsonSamples[i];
            Mdp<?> statusMessage = gson.fromJson(json, Mdp.class);
            assertEquals(Mdp.Cat.STATUS, statusMessage.getCat());
            assertInstanceOf(StatusValue.class, statusMessage.getValue());
            assertEquals(correctStatuses[i], statusMessage.getValue().toString());
        }
    }

    @Test
    void testObstaclesMessageSerialization() {
        ObstaclesValue obstaclesValue = new ObstaclesValue(
                Arrays.asList(
                        new Obstacle(5, 10, 1, 2),
                        new Obstacle(10, 20, 2, 1)
                ),
                "0"
        );
        Mdp<ObstaclesValue> obstaclesMessage = new Mdp<>(Mdp.Cat.OBSTACLES, obstaclesValue);
        String json = gson.toJson(obstaclesMessage);
        assertEquals("{\"cat\":\"obstacles\",\"value\":{\"obstacles\":[{\"x\":5,\"y\":10,\"id\":1,\"d\":2},{\"x\":10,\"y\":20,\"id\":2,\"d\":1}],\"mode\":\"0\"}}", json);
    }

    @Test
    void testObstaclesMessageDeserialization() {
        String json = "{\"cat\":\"obstacles\",\"value\":{\"obstacles\":[{\"x\":5,\"y\":10,\"id\":1,\"d\":2},{\"x\":10,\"y\":20,\"id\":2,\"d\":1}],\"mode\":\"0\"}}";
        Mdp<?> obstaclesMessage = gson.fromJson(json, Mdp.class);
        assertEquals(Mdp.Cat.OBSTACLES, obstaclesMessage.getCat());
        assertInstanceOf(ObstaclesValue.class, obstaclesMessage.getValue());
        ObstaclesValue obstaclesValue = (ObstaclesValue) obstaclesMessage.getValue();
        assertEquals(2, obstaclesValue.getObstacles().size());
        assertEquals(5, obstaclesValue.getObstacles().get(0).getX());
        assertEquals(10, obstaclesValue.getObstacles().get(0).getY());
        assertEquals(1, obstaclesValue.getObstacles().get(0).getId());
        assertEquals(2, obstaclesValue.getObstacles().get(0).getD());
        assertEquals("0", obstaclesValue.getMode());
    }

    @Test
    void testControlMessageSerialization() {
        ParameterizedControlValue controlValue = new ParameterizedControlValue("FW", 90);
        Mdp<ParameterizedControlValue> controlMessage = new Mdp<>(Mdp.Cat.CONTROL, controlValue);
        String json = gson.toJson(controlMessage);
        assertEquals("{\"cat\":\"control\",\"value\":\"FW090\"}", json);
    }

    @Test
    void testControlMessageDeserialization() {
        String[] controlJsonSamples = {
                "{\"cat\":\"control\",\"value\":\"start\"}",
                "{\"cat\":\"control\",\"value\":\"FW090\"}",
                "{\"cat\":\"control\",\"value\":\"BW085\"}",
                "{\"cat\":\"control\",\"value\":\"TL090\"}",
                "{\"cat\":\"control\",\"value\":\"TR090\"}",
                "{\"cat\":\"control\",\"value\":\"BL090\"}",
                "{\"cat\":\"control\",\"value\":\"BR090\"}",
                "{\"cat\":\"control\",\"value\":\"SNAP1_L\"}",
                "{\"cat\":\"control\",\"value\":\"SNAP2_C\"}",
                "{\"cat\":\"control\",\"value\":\"SNAP3_R\"}"
        };

        String[] correctCommands = {"start", "FW090", "BW085", "TL090", "TR090", "BL090", "BR090", "SNAP1_L", "SNAP2_C", "SNAP3_R"};

        for (int i = 0; i < controlJsonSamples.length; i++) {
            String json = controlJsonSamples[i];
            Mdp<?> controlMessage = gson.fromJson(json, Mdp.class);
            assertEquals(Mdp.Cat.CONTROL, controlMessage.getCat());
            assertInstanceOf(String.class, controlMessage.getValue());
            assertEquals(correctCommands[i], controlMessage.getValue());
        }
    }

    @Test
    void testInvalidMessageHandling() {
        String invalidJson = "{\"cat\":\"invalid\",\"value\":\"test\"}";
        assertThrows(IllegalArgumentException.class, () -> {
            gson.fromJson(invalidJson, Mdp.class);
        });
    }

    @Test
    void testInfoMessageRoundTrip() {
        for (InfoValue infoValue : InfoValue.values()) {
            Mdp<InfoValue> original = new Mdp<>(Mdp.Cat.INFO, infoValue);
            String json = gson.toJson(original);
            Mdp<?> deserialized = gson.fromJson(json, Mdp.class);

            assertEquals(Mdp.Cat.INFO, deserialized.getCat());
            assertInstanceOf(InfoValue.class, deserialized.getValue());
            assertEquals(infoValue, deserialized.getValue());
        }
    }

    @Test
    void testErrorMessageRoundTrip() {
        for (ErrorValue errorValue : ErrorValue.values()) {
            Mdp<ErrorValue> original = new Mdp<>(Mdp.Cat.ERROR, errorValue);
            String json = gson.toJson(original);
            Mdp<?> deserialized = gson.fromJson(json, Mdp.class);

            assertEquals(Mdp.Cat.ERROR, deserialized.getCat());
            assertInstanceOf(ErrorValue.class, deserialized.getValue());
            assertEquals(errorValue, deserialized.getValue());
        }
    }

    @Test
    void testStatusMessageRoundTrip() {
        for (StatusValue statusValue : StatusValue.values()) {
            Mdp<StatusValue> original = new Mdp<>(Mdp.Cat.STATUS, statusValue);
            String json = gson.toJson(original);
            Mdp<?> deserialized = gson.fromJson(json, Mdp.class);

            assertEquals(Mdp.Cat.STATUS, deserialized.getCat());
            assertInstanceOf(StatusValue.class, deserialized.getValue());
            assertEquals(statusValue, deserialized.getValue());
        }
    }

    @Test
    void testLocationMessageRoundTrip() {
        LocationValue location = new LocationValue(1, 2, 3);
        Mdp<LocationValue> original = new Mdp<>(Mdp.Cat.LOCATION, location);
        String json = gson.toJson(original);
        Mdp<?> deserialized = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.LOCATION, deserialized.getCat());
        assertInstanceOf(LocationValue.class, deserialized.getValue());
        LocationValue deserializedLocation = (LocationValue) deserialized.getValue();
        assertEquals(location.getX(), deserializedLocation.getX());
        assertEquals(location.getY(), deserializedLocation.getY());
        assertEquals(location.getD(), deserializedLocation.getD());
    }

    @Test
    void testImageRecognitionMessageRoundTrip() {
        ImageRecValue imageRec = new ImageRecValue("A", "1");
        Mdp<ImageRecValue> original = new Mdp<>(Mdp.Cat.IMAGE_REC, imageRec);
        String json = gson.toJson(original);
        Mdp<?> deserialized = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.IMAGE_REC, deserialized.getCat());
        assertInstanceOf(ImageRecValue.class, deserialized.getValue());

        ImageRecValue deserializedImageRec = (ImageRecValue) deserialized.getValue();
        assertEquals(imageRec.getImageId(), deserializedImageRec.getImageId());
        assertEquals(imageRec.getObstacleId(), deserializedImageRec.getObstacleId());

        ImageRecValue imageRec2 = new ImageRecValue("B", "2");
        Mdp<ImageRecValue> original2 = new Mdp<>(Mdp.Cat.IMAGE_REC, imageRec2);
        String json2 = gson.toJson(original2);
        Mdp<?> deserialized2 = gson.fromJson(json2, Mdp.class);

        assertEquals(Mdp.Cat.IMAGE_REC, deserialized2.getCat());
        assertInstanceOf(ImageRecValue.class, deserialized2.getValue());

        ImageRecValue deserializedImageRec2 = (ImageRecValue) deserialized2.getValue();
        assertEquals(imageRec2.getImageId(), deserializedImageRec2.getImageId());
        assertEquals(imageRec2.getObstacleId(), deserializedImageRec2.getObstacleId());
    }

    @Test
    void testObstaclesMessageRoundTrip() {
        ObstaclesValue obstaclesValue = new ObstaclesValue(
                Arrays.asList(
                        new Obstacle(5, 10, 1, 2),
                        new Obstacle(10, 20, 2, 1)
                ),
                "0"
        );
        Mdp<ObstaclesValue> original = new Mdp<>(Mdp.Cat.OBSTACLES, obstaclesValue);
        String json = gson.toJson(original);
        Mdp<?> deserialized = gson.fromJson(json, Mdp.class);

        assertEquals(Mdp.Cat.OBSTACLES, deserialized.getCat());
        assertInstanceOf(ObstaclesValue.class, deserialized.getValue());
        ObstaclesValue deserializedObstacles = (ObstaclesValue) deserialized.getValue();
        assertEquals(obstaclesValue.getObstacles().size(), deserializedObstacles.getObstacles().size());
        assertEquals(obstaclesValue.getMode(), deserializedObstacles.getMode());

        for (int i = 0; i < obstaclesValue.getObstacles().size(); i++) {
            Obstacle original_obstacle = obstaclesValue.getObstacles().get(i);
            Obstacle deserialized_obstacle = deserializedObstacles.getObstacles().get(i);
            assertEquals(original_obstacle.getX(), deserialized_obstacle.getX());
            assertEquals(original_obstacle.getY(), deserialized_obstacle.getY());
            assertEquals(original_obstacle.getId(), deserialized_obstacle.getId());
            assertEquals(original_obstacle.getD(), deserialized_obstacle.getD());
        }
    }

    @Test
    void testControlMessageRoundTrip() {
        String[] controlCommands = {"start", "FW090", "BW085", "TL090", "TR090", "BL090", "BR090", "SNAP1_L", "SNAP2_C", "SNAP3_R"};
        for (String command : controlCommands) {
            ParameterizedControlValue controlValue = ParameterizedControlValue.fromString(command);
            Mdp<ParameterizedControlValue> original = new Mdp<>(Mdp.Cat.CONTROL, controlValue);
            String json = gson.toJson(original);
            Mdp<?> deserialized = gson.fromJson(json, Mdp.class);

            assertEquals(Mdp.Cat.CONTROL, deserialized.getCat());
            assertInstanceOf(String.class, deserialized.getValue());
            assertEquals(command, deserialized.getValue());
        }
    }
}
