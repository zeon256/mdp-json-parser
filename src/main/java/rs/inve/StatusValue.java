package rs.inve;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public enum StatusValue {

    @SerializedName("running")
    RUNNING("running"),
    @SerializedName("finished")
    FINISHED("finished");
    private final String value;
    private final static Map<String, StatusValue> CONSTANTS = new HashMap<>();

    static {
        for (StatusValue c : values()) {
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

    public static StatusValue fromValue(String value) {
        StatusValue constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
