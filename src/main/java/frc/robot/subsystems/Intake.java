// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkPIDController;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants.ModuleConstants;

public class Intake {
  private final CANSparkMax m_intakeMotorLeft;
  private final CANSparkMax m_intakeMotorRight;

  private final RelativeEncoder m_leftEncoder;
  private final RelativeEncoder m_rightEncoder;

  private final SparkPIDController m_intakeMotorRightPIDController;
  private final SparkPIDController m_intakeMotorLeftPIDController;

  public Intake(int leftCANID, int rightCANID) {
    m_intakeMotorRight = new CANSparkMax(leftCANID, MotorType.kBrushless);
    m_intakeMotorLeft = new CANSparkMax(rightCANID, MotorType.kBrushless);

    // Factory reset, so we get the SPARKS MAX to a known state before configuring
    // them. This is useful in case a SPARK MAX is swapped out.
    m_intakeMotorLeft.restoreFactoryDefaults();
    m_intakeMotorRight.restoreFactoryDefaults();

    // Setup encoders and PID controllers for the left and right SPARKS MAX.
    m_leftEncoder = m_intakeMotorLeft.getEncoder();
    m_rightEncoder = m_intakeMotorRight.getEncoder();
    m_intakeMotorLeftPIDController = m_intakeMotorLeft.getPIDController();
    m_intakeMotorLeftPIDController.setFeedbackDevice(m_leftEncoder);
    m_intakeMotorRightPIDController = m_intakeMotorRight.getPIDController();
    m_intakeMotorRightPIDController.setFeedbackDevice(m_rightEncoder);

    // Apply position and velocity conversion factors for the motor encoders. The
    // native unit for velocity is RPM, but we want meters per second for human input.
    m_leftEncoder.setVelocityConversionFactor(ModuleConstants.kDrivingEncoderVelocityFactor);
    m_rightEncoder.setVelocityConversionFactor(ModuleConstants.kDrivingEncoderVelocityFactor);

    // Set the PID gains for the motors. Note these are example gains, and you
    // may need to tune them for your own robot!
    m_intakeMotorLeftPIDController.setP(ModuleConstants.kIntakeP);
    m_intakeMotorLeftPIDController.setI(ModuleConstants.kIntakeI);
    m_intakeMotorLeftPIDController.setD(ModuleConstants.kIntakeD);
    m_intakeMotorLeftPIDController.setFF(ModuleConstants.kIntakeFF);
    m_intakeMotorLeftPIDController.setOutputRange(ModuleConstants.kIntakeMinOutput,
        ModuleConstants.kIntakeMaxOutput);

    m_intakeMotorRightPIDController.setP(ModuleConstants.kIntakeP);
    m_intakeMotorRightPIDController.setI(ModuleConstants.kIntakeI);
    m_intakeMotorRightPIDController.setD(ModuleConstants.kIntakeD);
    m_intakeMotorRightPIDController.setFF(ModuleConstants.kIntakeFF);
    m_intakeMotorRightPIDController.setOutputRange(ModuleConstants.kIntakeMinOutput,
        ModuleConstants.kIntakeMaxOutput);

    m_intakeMotorLeft.setIdleMode(ModuleConstants.kIntakeMotorIdleMode);
    m_intakeMotorLeft.setSmartCurrentLimit(ModuleConstants.kIntakeMotorCurrentLimit);
    m_intakeMotorRight.setIdleMode(ModuleConstants.kIntakeMotorIdleMode);
    m_intakeMotorRight.setSmartCurrentLimit(ModuleConstants.kIntakeMotorCurrentLimit);

    // Save the SPARK MAX configurations. If a SPARK MAX browns out during
    // operation, it will maintain the above configurations.
    m_intakeMotorLeft.burnFlash();
    m_intakeMotorRight.burnFlash();
  }

  public void setDesiredVelocity(double desiredVelocity) {
    // Command intake motors towards their respective setpoints, with one motor being flipped
    m_intakeMotorLeftPIDController.setReference(desiredVelocity, CANSparkMax.ControlType.kVelocity);
    m_intakeMotorRightPIDController.setReference(-desiredVelocity, CANSparkMax.ControlType.kVelocity);
  }
}