
package rs.inve;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Mdp {
    @SerializedName("cat")
    @Expose
    private Mdp.Cat cat;

    @SerializedName("value")
    @Expose
    private Value value;


    public Mdp() {
    }

    public Mdp(Mdp.Cat cat, Value value) {
        super();
        this.cat = cat;
        this.value = value;
    }


    public Mdp.Cat getCat() {
        return cat;
    }
    public void setCat(Mdp.Cat cat) {
        this.cat = cat;
    }

    public Mdp withCat(Mdp.Cat cat) {
        this.cat = cat;
        return this;
    }

    public Value getValue() {
        return value;
    }
    public void setValue(Value value) {
        this.value = value;
    }

    public Mdp withValue(Value value) {
        this.value = value;
        return this;
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
        private final static Map<String, Mdp.Cat> CONSTANTS = new HashMap<String, Mdp.Cat>();

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
