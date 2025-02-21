package com.github.zeon256;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageRecValue {

    @SerializedName("image_id")
    @Expose
    private String imageId;

    @SerializedName("obstacle_id")
    @Expose
    private String obstacleId;

    public ImageRecValue() {
    }

    public ImageRecValue(String imageId, String obstacleId) {
        super();
        this.imageId = imageId;
        this.obstacleId = obstacleId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getObstacleId() {
        return obstacleId;
    }

    public void setObstacleId(String obstacleId) {
        this.obstacleId = obstacleId;
    }
}
