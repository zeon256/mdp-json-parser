package com.github.zeon256;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObstaclesValue {

    @SerializedName("obstacles")
    @Expose
    private List<Obstacle> obstacles;

    @SerializedName("mode")
    @Expose
    private String mode;


    public ObstaclesValue() {
    }

    public ObstaclesValue(List<Obstacle> obstacles, String mode) {
        super();
        this.obstacles = obstacles;
        this.mode = mode;
    }


    public List<Obstacle> getObstacles() {
        return obstacles;
    }
    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public ObstaclesValue withObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
        return this;
    }

    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    public ObstaclesValue withMode(String mode) {
        this.mode = mode;
        return this;
    }
}
