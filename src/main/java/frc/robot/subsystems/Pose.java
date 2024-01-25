package frc.robot.subsystems;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.Constants;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.Vector;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.util.Units;
import frc.robot.Constants.DriveConstants;
//import frc.robot.commands.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Vision.*;
import frc.robot.subsystems.Vision.LimelightHelpers.LimelightTarget_Fiducial;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/* 
public class Pose {
    Field2d field2d = new Field2d();
    private final SwerveDrivePoseEstimator poseEstimator;
    private final DriveSubsystem drive;
    public Pose (DriveSubsystem drive) {
        this.drive = drive;
        poseEstimator = new SwerveDrivePoseEstimator(
            DriveConstants.kDriveKinematics, 
            drive.getRotation(), 
            drive.getModulePositions(), 
            new Pose2d());
        
        
    }
    public void periodic() {
        if (DriverStation.getAlliance().get()==Alliance.Blue) {
            var blueFrontResult = LimelightHelpers.getLatestResults(Constants.Limelight.front).targetingResults;
            Pose2d blueFrontBotPose = blueFrontResult.getBotPose2d_wpiBlue();
            double frontTimestamp = Timer.getFPGATimestamp() - (blueFrontResult.latency_capture/1000.0) - (blueFrontResult.latency_pipeline/1000.0);
         //   if(blueFrontResult.targets_Fiducials.length > 0) {
          //      if (getAvgTA(blueFrontResult.targets_Fiducials) > 0 ) {
                    poseEstimator.addVisionMeasurement(blueFrontBotPose, frontTimestamp);
                 //   field2d.setRobotPose(blueFrontBotPose.relativeTo(Constants.BlueOrigin));
                }
            }
            
        }
  
          if (DriverStation.getAlliance().get() == Alliance.Red) {
          //  var redFrontResult = LimelightHelpers.getLatestResults(Constants.Limelight.front).targetingResults;
          //  Pose2d redFrontBotPose = redFrontResult.getBotPose2d_wpiRed();
          //  double frontTimestamp = Timer.getFPGATimestamp() - (redFrontResult.latency_capture/1000.0) - (redFrontResult.latency_pipeline/1000.0);
            if(redFrontResult.targets_Fiducials.length > 0) {
                if (getAvgTA(redFrontResult.targets_Fiducials) > 0 ) {
                    poseEstimator.addVisionMeasurement(redFrontBotPose, frontTimestamp);
                    field2d.setRobotPose(redFrontBotPose.relativeTo(Constants.RedOrigin));
                }
            }
        }
        //poseEstimator.update(drive.getRotation(), drive.getModulePositions());
        SmartDashboard.putData("Field pose", field2d);
        
    }
    public double getAvgTA(LimelightTarget_Fiducial[] fiducials){
        double sumTA = 0;
        for(int i = 0; i < fiducials.length; i++){
          sumTA += fiducials[i].ta;
        }
        return sumTA / fiducials.length;
      }
    
}
*/
public class Pose {
    Field2d field2d = new Field2d();
    private final SwerveDrivePoseEstimator poseEstimator;
    private final DriveSubsystem drive;
    public Pose (DriveSubsystem drive) {
        this.drive = drive;
        poseEstimator = new SwerveDrivePoseEstimator(
            DriveConstants.kDriveKinematics, 
            drive.getRotation(), 
            drive.getModulePositions(), 
            new Pose2d());
        
    }
    public void periodic() {
        Vision photonPose = new Vision();
        var visionEst = photonPose.getEstimatedGlobalPose();
        // Change our trust in the measurement based on the tags we can see
        visionEst.ifPresent(
                est -> {
                    var estPose = est.estimatedPose.toPose2d();
                    // Change our trust in the measurement based on the tags we can see
                    var estStdDevs = photonPose.getEstimationStdDevs(estPose);

                    poseEstimator.addVisionMeasurement(
                            est.estimatedPose.toPose2d(), est.timestampSeconds, estStdDevs);
                });
    }
}
