package rs.inve;

public enum ControlValue {
    START("start"),
    STOP("STOP"),
    FIN("FIN"),
    RS000("RS000"),
    ACK("ACK"),
    STM_STARTING_UP("STM Starting up!"),
    FW_INDEFINITE("FW---"),
    BW_INDEFINITE("BW---"),
    TL_INDEFINITE("TL---"),
    TR_INDEFINITE("TR---");

    private final String value;

    ControlValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static ControlValue fromString(String text) {
        for (ControlValue b : ControlValue.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
