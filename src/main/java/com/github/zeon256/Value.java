package com.github.zeon256;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("infoValue")
    @Expose
    private InfoValue infoValue;
    @SerializedName("errorValue")
    @Expose
    private ErrorValue errorValue;
    @SerializedName("locationValue")
    @Expose
    private LocationValue locationValue;
    @SerializedName("imageRecValue")
    @Expose
    private ImageRecValue imageRecValue;
    @SerializedName("statusValue")
    @Expose
    private StatusValue statusValue;
    @SerializedName("obstaclesValue")
    @Expose
    private ObstaclesValue obstaclesValue;
    @SerializedName("controlValue")
    @Expose
    private ControlValue controlValue;

    public Value() {
    }

    public Value(InfoValue infoValue, ErrorValue errorValue, LocationValue locationValue, ImageRecValue imageRecValue, StatusValue statusValue, ObstaclesValue obstaclesValue, ControlValue controlValue) {
        super();
        this.infoValue = infoValue;
        this.errorValue = errorValue;
        this.locationValue = locationValue;
        this.imageRecValue = imageRecValue;
        this.statusValue = statusValue;
        this.obstaclesValue = obstaclesValue;
        this.controlValue = controlValue;
    }

    public InfoValue getInfoValue() {
        return infoValue;
    }
    public void setInfoValue(InfoValue infoValue) {
        this.infoValue = infoValue;
    }
    public ErrorValue getErrorValue() {
        return errorValue;
    }
    public void setErrorValue(ErrorValue errorValue) {
        this.errorValue = errorValue;
    }
    public LocationValue getLocationValue() {
        return locationValue;
    }
    public void setLocationValue(LocationValue locationValue) {
        this.locationValue = locationValue;
    }
    public ImageRecValue getImageRecValue() {
        return imageRecValue;
    }
    public void setImageRecValue(ImageRecValue imageRecValue) {
        this.imageRecValue = imageRecValue;
    }
    public StatusValue getStatusValue() {
        return statusValue;
    }
    public void setStatusValue(StatusValue statusValue) {
        this.statusValue = statusValue;
    }
    public ObstaclesValue getObstaclesValue() {
        return obstaclesValue;
    }
    public void setObstaclesValue(ObstaclesValue obstaclesValue) {
        this.obstaclesValue = obstaclesValue;
    }
    public ControlValue getControlValue() {
        return controlValue;
    }
    public void setControlValue(ControlValue controlValue) {
        this.controlValue = controlValue;
    }
}
