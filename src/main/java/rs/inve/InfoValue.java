package rs.inve;

import com.google.gson.annotations.SerializedName;

public enum InfoValue {
    @SerializedName("You are connected to the RPi!")
    CONNECTED("You are connected to the RPi!"),

    @SerializedName("Robot is ready!")
    ROBOT_READY("Robot is ready!"),

    @SerializedName("You are reconnected!")
    RECONNECTED("You are reconnected!"),

    @SerializedName("Starting robot on path!")
    STARTING_ROBOT_ON_PATH("Starting robot on path!"),

    @SerializedName("Commands queue finished!")
    COMMANDS_QUEUE_FINISHED("Commands queue finished!"),

    @SerializedName("Requesting path from algo...")
    REQUESTING_PATH("Requesting path from algo..."),

    @SerializedName("Commands and path received Algo API. Robot is ready to move")
    COMMANDS_AND_PATH_RECEIVED("Commands and path received Algo API. Robot is ready to move"),

    @SerializedName("Images stitched!")
    IMAGES_STITCHED("Images stitched!");

    private final String value;

    InfoValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}