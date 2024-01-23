package frc.utils;
import java.lang.Math;

public class OakCameraObject {

    private final double xAngle;
    private final double yAngle;
    private final int area;
    private final int distanceCamera;
    private final String type;
    private final double distance;

    public OakCameraObject(String objectData) {
        String[] splitData = objectData.split(", ");
        this.xAngle = Math.toRadians(Float.parseFloat(splitData[0]));
        this.yAngle = Math.toRadians(Float.parseFloat(splitData[1]));
        this.area = Integer.parseInt(splitData[2]);
        this.distanceCamera = Integer.parseInt(splitData[3]);
        this.type = splitData[4];
        this.distance = distanceCamera * Math.sin(yAngle - 90);
    }

    public double getXAngle() {
        return this.xAngle;
    }
    public double getYAngle() {
        return this.yAngle;
    }
    public int getArea() {
        return this.area;    
    }
    public int getDistanceCamera() {
        return this.distanceCamera;    
    }
    public String gettype() {
        return this.type;   
    }
    public Double getDistance() {
        return this.distance;  
    }
}