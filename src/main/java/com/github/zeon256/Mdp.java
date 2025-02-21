
package com.github.zeon256;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mdp<T> {
    @SerializedName("cat")
    @Expose
    private Mdp.Cat cat;

    @SerializedName("value")
    @Expose
    private T value;

    public Mdp() {
    }

    public Mdp(Mdp.Cat cat, T value) {
        this.cat = cat;
        this.value = value;
    }

    public Mdp.Cat getCat() {
        return cat;
    }

    public void setCat(Mdp.Cat cat) {
        this.cat = cat;
    }

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public enum Cat {
        @SerializedName("info")
        INFO("info"),
        @SerializedName("error")
        ERROR("error"),
        @SerializedName("location")
        LOCATION("location"),
        @SerializedName("image-rec")
        IMAGE_REC("image-rec"),
        @SerializedName("status")
        STATUS("status"),
        @SerializedName("obstacles")
        OBSTACLES("obstacles"),
        @SerializedName("control")
        CONTROL("control");

        private final String value;
        private final static Map<String, Mdp.Cat> CONSTANTS = new HashMap<>();

        static {
            for (Mdp.Cat c : values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Cat(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
        public String value() {
            return this.value;
        }

        public static Mdp.Cat fromValue(String value) {
            Mdp.Cat constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }
}
