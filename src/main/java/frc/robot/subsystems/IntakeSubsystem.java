// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.MAXMotionConfig.MAXMotionPositionMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class IntakeSubsystem extends SubsystemBase {
  
  private SparkMax m_intakeWrist;
  SparkMaxConfig intakeWristConfig;

  private RelativeEncoder encoder_intakeWrist;

  private SparkClosedLoopController feedbackControllerintakeWrist;



  public IntakeSubsystem() {
    m_intakeWrist = new SparkMax(IntakeConstants.kintakeWristMotorID, MotorType.kBrushless);

    intakeWristConfig = new SparkMaxConfig();

    configureMotors();

    encoder_intakeWrist = m_intakeWrist.getEncoder();
    feedbackControllerintakeWrist = m_intakeWrist.getClosedLoopController();

  }

@SuppressWarnings("removal")
public void configureMotors(){
    intakeWristConfig
    .inverted(IntakeConstants.kInverted);
    //.idleMode(ShooterConstants.kIdleMode);

    intakeWristConfig.inverted(IntakeConstants.kInverted);

    m_intakeWrist.configure(intakeWristConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public Command manualForwardBack(){
    return startEnd(
    () -> m_intakeWrist.set(1),
    () -> m_intakeWrist.set(0));
    }

  /*public Command spinAlgaeMotors(double position) {
    return runOnce(() -> {
      currentTargetPosition = position;
      feedbackControllerintakeWrist.setReference(position, ControlType.kPosition, ClosedLoopSlot.kSlot0);
    });
  }

@Override
public void periodic() {
  }

@Override
public void simulationPeriodic() {
  }
}
  */