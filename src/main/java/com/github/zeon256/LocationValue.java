
package com.github.zeon256;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationValue {

    @SerializedName("x")
    @Expose
    private int x;

    @SerializedName("y")
    @Expose
    private int y;

    @SerializedName("d")
    @Expose
    private int d;


    public LocationValue() {
    }

    public LocationValue(int x, int y, int d) {
        super();
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public LocationValue withX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public LocationValue withY(int y) {
        this.y = y;
        return this;
    }

    public int getD() {
        return d;
    }
    public void setD(int d) {
        this.d = d;
    }

    public LocationValue withD(int d) {
        this.d = d;
        return this;
    }
}
