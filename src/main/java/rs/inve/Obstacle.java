
package rs.inve;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Obstacle {
    @SerializedName("x")
    @Expose
    private int x;

    @SerializedName("y")
    @Expose
    private int y;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("d")
    @Expose
    private int d;


    public Obstacle() {
    }

    public Obstacle(int x, int y, int id, int d) {
        super();
        this.x = x;
        this.y = y;
        this.id = id;
        this.d = d;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getD() {
        return d;
    }
    public void setD(int d) {
        this.d = d;
    }
}
