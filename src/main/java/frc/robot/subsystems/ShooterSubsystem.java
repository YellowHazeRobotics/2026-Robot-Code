// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;
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

public class ShooterSubsystem extends SubsystemBase {
  
  private SparkMax m_ShooterBack;
  SparkMaxConfig shooterBackConfig;

  private RelativeEncoder encoder_ShooterBack;

  private SparkClosedLoopController feedbackController;

  public ShooterSubsystem() {
    m_ShooterBack = new SparkMax(ShooterConstants.kShooterBackMotorID, MotorType.kBrushless);

    shooterBackConfig = new SparkMaxConfig();

    configureMotors();

    encoder_ShooterBack = m_ShooterBack.getEncoder();
    feedbackController = m_ShooterBack.getClosedLoopController();
  }

@SuppressWarnings("removal")
public void configureMotors(){
    shooterBackConfig
    .inverted(ShooterConstants.kInverted)
    .idleMode(ShooterConstants.kIdleMode);

    m_ShooterBack.configure(shooterBackConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public Command manualForward(){
    return startEnd(
    () -> m_ShooterBack.set(0.5),
    () -> m_ShooterBack.set(0));
    }

  /*public Command spinAlgaeMotors(double position) {
    return runOnce(() -> {
      currentTargetPosition = position;
      feedbackController.setReference(position, ControlType.kPosition, ClosedLoopSlot.kSlot0);
    });
  }*/

@Override
public void periodic() {
  }

@Override
public void simulationPeriodic() {
  }
}
