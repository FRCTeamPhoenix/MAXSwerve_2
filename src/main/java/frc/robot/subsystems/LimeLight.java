package frc.robot.subsystems;


//import frc.robot.commands.*;
import edu.wpi.first.networktables.NetworkTableInstance;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {

private boolean m_LimelightHasValidTarget = false;
public double m_LimelightDriveX = 0.0;
public double m_LimelightDriveY = 0.0;
public double m_LimelightSteerCommand = 0.0;
private double m_targetArea = 0.0;

    public void limeLight() {

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        Update_Limelight_Tracking();
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public void Update_Limelight_Tracking(){
        // These numbers must be tuned for your Robot!  Be careful!
        final double STEER_K = 0.005;                  // How hard to turn toward the target
        final double DRIVE_K = 0.3;                    // How hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = 2.5;
        final double DESIRED_HEADING = 0;        // Area of the target when the robot reaches the wall
        final double MAX_DRIVE = 0.3;

        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        //double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
        m_targetArea = ta;

        double errArea = DESIRED_TARGET_AREA - ta;
        if (Math.abs(errArea) < 0.25) {
          errArea = 0;
        }

        double errAngle = DESIRED_HEADING - tx;
        if (Math.abs(errAngle) < 1) {
          errAngle = 0;
        }

        if (ta > DESIRED_TARGET_AREA){
          double speed = DRIVE_K * Math.sqrt(ta);
          double x = errArea * Math.cos(errAngle);
          if (Math.abs(x) < MAX_DRIVE) {
            x = MAX_DRIVE;
          }
          m_LimelightDriveX = x;
          double y = errArea * Math.sin(errAngle);
          if (Math.abs(y) < MAX_DRIVE) {
            y = MAX_DRIVE;
          }
          m_LimelightDriveY = y;
        }
  }

  public boolean hasValidTarget() {
      return m_LimelightHasValidTarget;
  }

 // public double getLLDriveSpeed() {
    //return m_LimelightDriveCommand;
  //}

  public double getLLTurnSpeed() {
    return m_LimelightSteerCommand;
  }

  public double getLLTargetArea() {
    return m_targetArea;
  }
}

