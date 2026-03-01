package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public final class Constants {

    public static class ShooterConstants{
        public static final int kShooterBackMotorID = 18;
        public static final int kShooterFrontMotorID = 15;
        public static final IdleMode kIdleMode = IdleMode.kBrake;
        public static final boolean kFrontInverted = true;
        public static final boolean kBackInverted = false;
    }

    public static class IntakeConstants{
        public static final IdleMode kIdleMode = IdleMode.kBrake;
        public static final boolean kInverted = true;
    }

}
