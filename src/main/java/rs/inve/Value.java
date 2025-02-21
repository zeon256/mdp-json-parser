
package rs.inve;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("infoValue")
    @Expose
    private Object infoValue;
    @SerializedName("errorValue")
    @Expose
    private Value.ErrorValue errorValue;
    @SerializedName("locationValue")
    @Expose
    private LocationValue locationValue;
    @SerializedName("imageRecValue")
    @Expose
    private ImageRecValue imageRecValue;
    @SerializedName("statusValue")
    @Expose
    private Value.StatusValue statusValue;
    @SerializedName("obstaclesValue")
    @Expose
    private ObstaclesValue obstaclesValue;
    @SerializedName("controlValue")
    @Expose
    private Object controlValue;

    public Value() {
    }

    public Value(Object infoValue, Value.ErrorValue errorValue, LocationValue locationValue, ImageRecValue imageRecValue, Value.StatusValue statusValue, ObstaclesValue obstaclesValue, Object controlValue) {
        super();
        this.infoValue = infoValue;
        this.errorValue = errorValue;
        this.locationValue = locationValue;
        this.imageRecValue = imageRecValue;
        this.statusValue = statusValue;
        this.obstaclesValue = obstaclesValue;
        this.controlValue = controlValue;
    }

    public Object getInfoValue() {
        return infoValue;
    }

    public void setInfoValue(Object infoValue) {
        this.infoValue = infoValue;
    }

    public Value withInfoValue(Object infoValue) {
        this.infoValue = infoValue;
        return this;
    }

    public Value.ErrorValue getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(Value.ErrorValue errorValue) {
        this.errorValue = errorValue;
    }

    public Value withErrorValue(Value.ErrorValue errorValue) {
        this.errorValue = errorValue;
        return this;
    }

    public LocationValue getLocationValue() {
        return locationValue;
    }

    public void setLocationValue(LocationValue locationValue) {
        this.locationValue = locationValue;
    }

    public Value withLocationValue(LocationValue locationValue) {
        this.locationValue = locationValue;
        return this;
    }

    public ImageRecValue getImageRecValue() {
        return imageRecValue;
    }

    public void setImageRecValue(ImageRecValue imageRecValue) {
        this.imageRecValue = imageRecValue;
    }

    public Value withImageRecValue(ImageRecValue imageRecValue) {
        this.imageRecValue = imageRecValue;
        return this;
    }

    public Value.StatusValue getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Value.StatusValue statusValue) {
        this.statusValue = statusValue;
    }

    public Value withStatusValue(Value.StatusValue statusValue) {
        this.statusValue = statusValue;
        return this;
    }

    public ObstaclesValue getObstaclesValue() {
        return obstaclesValue;
    }

    public void setObstaclesValue(ObstaclesValue obstaclesValue) {
        this.obstaclesValue = obstaclesValue;
    }

    public Value withObstaclesValue(ObstaclesValue obstaclesValue) {
        this.obstaclesValue = obstaclesValue;
        return this;
    }

    public Object getControlValue() {
        return controlValue;
    }

    public void setControlValue(Object controlValue) {
        this.controlValue = controlValue;
    }

    public Value withControlValue(Object controlValue) {
        this.controlValue = controlValue;
        return this;
    }

    public enum ErrorValue {
        @SerializedName("API is down, start command aborted.")
        API_IS_DOWN_START_COMMAND_ABORTED("API is down, start command aborted."),
        @SerializedName("Command queue is empty, did you set obstacles?")
        COMMAND_QUEUE_IS_EMPTY_DID_YOU_SET_OBSTACLES("Command queue is empty, did you set obstacles?"),
        @SerializedName("Something went wrong when requesting stitch from the API.")
        SOMETHING_WENT_WRONG_WHEN_REQUESTING_STITCH_FROM_THE_API("Something went wrong when requesting stitch from the API."),
        @SerializedName("Something went wrong when requesting path from Algo API.")
        SOMETHING_WENT_WRONG_WHEN_REQUESTING_PATH_FROM_ALGO_API("Something went wrong when requesting path from Algo API.");
        private final String value;
        private final static Map<String, Value.ErrorValue> CONSTANTS = new HashMap<String, Value.ErrorValue>();

        static {
            for (Value.ErrorValue c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        ErrorValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Value.ErrorValue fromValue(String value) {
            Value.ErrorValue constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public enum StatusValue {

        @SerializedName("running")
        RUNNING("running"),
        @SerializedName("finished")
        FINISHED("finished");
        private final String value;
        private final static Map<String, Value.StatusValue> CONSTANTS = new HashMap<String, Value.StatusValue>();

        static {
            for (Value.StatusValue c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        StatusValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Value.StatusValue fromValue(String value) {
            Value.StatusValue constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
