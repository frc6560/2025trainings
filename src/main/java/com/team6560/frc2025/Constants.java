package com.team6560.frc2025;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import com.frc3481.swervelib.math.Matter;
public final class Constants {

  public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag
  public static final double MAX_SPEED  = Units.feetToMeters(14.5);
  // Maximum speed of the robot in meters per second, used to limit acceleration.

//  public static final class AutonConstants
//  {
//
//    public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.7, 0, 0);
//    public static final PIDConstants ANGLE_PID       = new PIDConstants(0.4, 0, 0.01);
//  }

  public static final class DrivebaseConstants
  {
    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class OperatorConstants
  {
    // Joystick Deadband
    public static final double DEADBAND        = 0.1;
    public static final double LEFT_Y_DEADBAND = 0.1;
    public static final double RIGHT_X_DEADBAND = 0.1;
    public static final double TURN_CONSTANT    = 6;
  }

  public static final class ElevatorConstants {
    }

    public static final class WristConstants {  
      public static double PID_KP = 0.5; // Proportional gain for position control
      public static double PID_KI = 0.0; // Integral gain for position control
      public static double PID_KD = 0.0; // Derivative gain for position control
      public static double PID_KG = 0.0; // Feedforward gain, adjust as needed
      public static double PID_KS = 0.0; // Static gain, adjust as needed

      public static final int M_ID = 1; // Replace with actual CAN ID
      public static final int Encoder_ID = 2; // Replace with actual CAN ID

      public static double GEAR_RATIO = 1.0; // Sets gear ratio between motor and encoder

      public static double L1 = 0.0; // Position for Level 1
      public static double L2 = 0.0; // Position for Level 2
      public static double L3 = 0.0; // Position for Level 3
      public static double L4 = 0.0; // Position for Level 4
      public static double STOW = 0.0; // Position for Stow
      public static double INTAKE = 0.0; // Position for Intake
      public static double BARGE = 0.0; // Position for Barge

      public static final double UPPER_BOUND = 0.0;
      public static final double LOWER_BOUND = 0.0;


    }
  
    public static final class ClimbConstants {


    }
  }


    


  // public static final class ClimbConstants {


  // }