package frc.robot.subsystems;
import java.util.*;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

//import frc.robot.commands.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.utils.OakCameraObject;

public class OakCamera extends SubsystemBase {

  private List<OakCameraObject> cameraObjects = null;

  public List<OakCameraObject> extractOakData() {
    List<OakCameraObject> cameraObjects = new ArrayList<>();
    String[] cameraPredictions = NetworkTableInstance.getDefault().getTable("oakCamera").getEntry("cameraItems").getStringArray(null);
    for (int objectNumber = 0; objectNumber < cameraPredictions.length; objectNumber++ ) {
      cameraObjects.add(new OakCameraObject(cameraPredictions[objectNumber]));
    }
    return cameraObjects;
  }

  @Override
  public void periodic() {
      // This method will be called once per scheduler run
      extractOakData();
  }

  public OakCameraObject findClosestNote() {
    double minimumDistance = Integer.MAX_VALUE;
    OakCameraObject closestNote = null;
    // loop through ever object the cammera detects
    for (OakCameraObject objectInstance : cameraObjects) {
      //filter out non notes
      if (objectInstance.gettype() != "note") {
        continue;
      }
      //filter out notes that are too far
      if (objectInstance.getDistance() >= minimumDistance) {
        continue;
      }
      //update closest notes
      minimumDistance = objectInstance.getDistance();
      closestNote = objectInstance;
    }
    // return the colsest notes
    return closestNote;
  }

}

