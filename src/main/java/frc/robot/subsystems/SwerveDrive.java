package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

abstract class SwerveDrive {
   abstract SwerveModulePosition getPosition();
   abstract void setDesiredState(SwerveModuleState state);
   abstract void resetEncoders();
   abstract SwerveModuleState getState();
}

class TalonSwerve extends SwerveDrive {
   private TalonSwerveModule module;
   public TalonSwerve(int drivingCANId, int turningCANId, double chassisAngularOffset) {module = new TalonSwerveModule(drivingCANId, turningCANId, chassisAngularOffset);}
   public SwerveModulePosition getPosition(){return module.getPosition();}
   public void setDesiredState(SwerveModuleState state){module.setDesiredState(state);}
   public void resetEncoders() {module.resetEncoders();}
   public SwerveModuleState getState() {return module.getState();}
}

class MAXSwerve extends SwerveDrive {
   public MAXSwerveModule module;
   public MAXSwerve(int drivingCANId, int turningCANId, double chassisAngularOffset) {module = new MAXSwerveModule(drivingCANId, turningCANId, chassisAngularOffset);}
   public SwerveModulePosition getPosition(){return module.getPosition();}
   public void setDesiredState(SwerveModuleState state){module.setDesiredState(state);}
   public void resetEncoders() {module.resetEncoders();}
   public SwerveModuleState getState() {return module.getState();}
}
