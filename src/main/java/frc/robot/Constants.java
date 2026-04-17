package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public final class Constants {

    public static class ShooterConstants{
        public static final int kShooterBackMotorID = 20;
        public static final int kShooterFrontMotorID = 16;
        public static final int kShooterSecondBackMotorID = 15;
        public static final IdleMode kIdleMode = IdleMode.kBrake;
        public static final boolean kFrontInverted = true;
        public static final boolean kBackInverted = true;
        public static final boolean kSecondBackInverted = false;
    }

    public static class IntakeConstants{
        public static final IdleMode kIdleMode = IdleMode.kBrake;
        public static final boolean kInverted = true;
        public static final int kIntakeWristMotorID = 23;
        public static final int kIntakeMotorID = 24;
        public static final boolean kWristInverted = false;
        public static final boolean kIntakeInverted = false;
    }

}
