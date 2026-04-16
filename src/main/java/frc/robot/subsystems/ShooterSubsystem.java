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
import com.ctre.phoenix6.configs.Pigeon2Configurator;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

public class ShooterSubsystem extends SubsystemBase {
  
  private SparkMax m_ShooterBack;
  SparkMaxConfig shooterBackConfig;

  private RelativeEncoder encoder_ShooterBack;

  private SparkClosedLoopController feedbackControllerShooterBack;


  private SparkMax m_ShooterFront;
  SparkMaxConfig shooterFrontConfig;

  private RelativeEncoder encoder_ShooterFront;

  private SparkClosedLoopController feedbackControllerShooterFront;


  private SparkMax m_ShooterSecondBack;
  SparkMaxConfig shooterSecondBackConfig;

  private RelativeEncoder encoder_ShooterSecondBack;

  private SparkClosedLoopController feedbackControllerShooterSecondBack;

  final TalonFX krakenBridge;
  final DutyCycleOut krakenBridge_request;


  public ShooterSubsystem() {
    m_ShooterBack = new SparkMax(ShooterConstants.kShooterBackMotorID, MotorType.kBrushless);
    m_ShooterFront = new SparkMax(ShooterConstants.kShooterFrontMotorID, MotorType.kBrushless);
    m_ShooterSecondBack = new SparkMax(ShooterConstants.kShooterSecondBackMotorID, MotorType.kBrushless);

    krakenBridge = new TalonFX(21);
    krakenBridge_request = new DutyCycleOut(0.0);

    shooterBackConfig = new SparkMaxConfig();
    shooterFrontConfig = new SparkMaxConfig();
    shooterSecondBackConfig = new SparkMaxConfig();

    configureMotors();

    encoder_ShooterBack = m_ShooterBack.getEncoder();
    feedbackControllerShooterBack = m_ShooterBack.getClosedLoopController();

    encoder_ShooterFront = m_ShooterFront.getEncoder();
    feedbackControllerShooterFront = m_ShooterFront.getClosedLoopController();

    encoder_ShooterSecondBack = m_ShooterSecondBack.getEncoder();
    feedbackControllerShooterSecondBack = m_ShooterSecondBack.getClosedLoopController();
  }

@SuppressWarnings("removal")
public void configureMotors(){
    //shooterBackConfig.inverted(ShooterConstants.kBackInverted);
    //.idleMode(ShooterConstants.kIdleMode);
    //m_ShooterBack.configureAsync(shooterBackConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    //shooterFrontConfig.apply(shooterBackConfig);
    //shooterFrontConfig.follow(m_ShooterBack, ShooterConstants.kFrontInverted);
    shooterFrontConfig.inverted(ShooterConstants.kFrontInverted);
    m_ShooterFront.configureAsync(shooterFrontConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


    shooterSecondBackConfig.follow(m_ShooterFront, ShooterConstants.kSecondBackInverted);
    shooterSecondBackConfig.inverted(ShooterConstants.kSecondBackInverted);
    m_ShooterSecondBack.configureAsync(shooterSecondBackConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    shooterBackConfig.follow(m_ShooterFront, ShooterConstants.kBackInverted);
    shooterBackConfig.inverted(ShooterConstants.kBackInverted);
    m_ShooterBack.configureAsync(shooterBackConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
  }

  public Command manualForwardBack(){
    return startEnd(
    () -> m_ShooterBack.set(1),
    () -> m_ShooterBack.set(0));
    }


public Command runAxle(double speed) {
  return run(() -> krakenBridge.setControl(krakenBridge_request.withOutput(speed))).finallyDo(() -> krakenBridge.setControl(krakenBridge_request.withOutput(0)));
  }

  public Command manualShooterSecondBack(){
    return startEnd(
    () -> m_ShooterSecondBack.set(1),
    () -> m_ShooterSecondBack.set(0));
    }


  public Command manualForwardFront(){
    return startEnd(
    () -> m_ShooterFront.set(1),
    () -> m_ShooterFront.set(0));
    }

  public Command manualSecondBack(){
    return startEnd(
    () -> m_ShooterFront.set(1),
    () -> m_ShooterFront.set(0));
    }

    public Command manuallyRunForward() {
        return run(() -> {
            m_ShooterBack.set(1);
        });
    }


@Override
public void periodic() {
  }

@Override
public void simulationPeriodic() {
  }
}
