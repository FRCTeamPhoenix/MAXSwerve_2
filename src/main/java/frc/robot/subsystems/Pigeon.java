package frc.robot.subsystems;

import com.ctre.phoenix.sensors.*;  // Firmware Pigeon2-vS-Application-22.7.3.0-season2023.crf (Phoenix 5)
import com.ctre.phoenix6.hardware.Pigeon2;
import frc.robot.Constants;

public class Pigeon {
    private Pigeon2 pigeon2 = new Pigeon2(0);
    private WPI_PigeonIMU pigeon = new WPI_PigeonIMU(0);

    public void setupPigeon(int deviceID, String canbus) {
        if (Constants.DriveConstants.usingPigeon2) {
            pigeon2 = new Pigeon2(deviceID,canbus);
        }
        else {
            pigeon = new WPI_PigeonIMU(deviceID);
        }
    }
    public double getYaw(){
        if (Constants.DriveConstants.usingPigeon2) {
            return pigeon2.getYaw().getValueAsDouble();
        }
        else {
            return pigeon.getYaw();
        }
    }
    public double getPitch(){
        if (Constants.DriveConstants.usingPigeon2) {
            return pigeon2.getPitch().getValueAsDouble();
        }
        else {
            return pigeon.getPitch();
        }
    }
    public double getRoll(){
        if (Constants.DriveConstants.usingPigeon2) {
            return pigeon2.getRoll().getValueAsDouble();
        }
        else {
            return pigeon.getRoll();
        }
    }

    public double getRate(){
        if (Constants.DriveConstants.usingPigeon2) {
            return pigeon2.getRate();
        }
        else {
            return pigeon.getRate();
        }
    }

    public void reset(){
        if (Constants.DriveConstants.usingPigeon2) {
            pigeon2.reset();
        }
        else {
            pigeon.reset();
        }
    }

    public Pigeon2 getPigeon2(){
        return pigeon2;
    }

    public WPI_PigeonIMU getPigeon(){
        return pigeon;
    }
}
