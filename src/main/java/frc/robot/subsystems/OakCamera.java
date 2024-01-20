package frc.robot.subsystems;


//import frc.robot.commands.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OakCamera extends SubsystemBase {


  public extractOakData() {
    private List<oakCameraObject> cameraObjects;
    private String[] cameraPredictions = NetworkTableInstance.getDefault().getTable("oakCamera").getEntry("cameraItems").getStringArray(0)
    for (String objectData : cameraPredictions) {
      cameraObjects.add(new oakCameraObject(cameraPredictions[objectData]));
    }
    return cameraObjects;
  }

  @Override
  public void periodic() {
      // This method will be called once per scheduler run
      extractOakData();
  }

  public boolean findClosestNote() {
    private minimumDistance;
    private closestNote;
    for (oakCameraObject objectNumber : cameraObjects) {
      if cameraObjects[objectNumber].returnData(type) != "note"{
        continue;
      }
      if 
    }
  }

 // public double getLLDriveSpeed() {
    //return m_LimelightDriveCommand;
  //}

  public double getLLTurnSpeed() {
    return m_LimelightDriveRot;
  }

  public double getLLTargetArea() {
    return m_targetArea;
  }
}

