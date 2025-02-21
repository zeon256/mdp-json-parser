package com.github.zeon256;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public enum ErrorValue {
    @SerializedName("API is down, start command aborted.")
    API_DOWN("API is down, start command aborted."),
    @SerializedName("Command queue is empty, did you set obstacles?")
    COMMAND_QUEUE_IS_EMPTY_DID_YOU_SET_OBSTACLES("Command queue is empty, did you set obstacles?"),
    @SerializedName("Something went wrong when requesting stitch from the API.")
    SOMETHING_WENT_WRONG_WHEN_REQUESTING_STITCH_FROM_THE_API("Something went wrong when requesting stitch from the API."),
    @SerializedName("Something went wrong when requesting path from Algo API.")
    SOMETHING_WENT_WRONG_WHEN_REQUESTING_PATH_FROM_ALGO_API("Something went wrong when requesting path from Algo API.");
    private final String value;
    private final static Map<String, ErrorValue> CONSTANTS = new HashMap<>();

    static {
        for (ErrorValue c : values()) {
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

    public static ErrorValue fromValue(String value) {
        ErrorValue constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
