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
// substitiute with actual values later
  public static final int GEAR_RATIO = 108; // 108:1 gear ratio for the wrist motor
  public static final int M_ID = 16; // CAN ID for the wrist motor
  public static final int Encoder_ID = 17; // CAN ID for the wrist encoder
  public static final double PID_KP = 1.0; // Proportional gain for wrist position control
  public static final double PID_KI = 0.01; // Integral gain for wrist position control
  public static final double PID_KD = 0.0; // Derivative gain for wrist position control
  public static final double UPPER_BOUND = 90.0; // Maximum angle for the wrist in degrees
  public static final double LOWER_BOUND = -90.0; // Minimum angle for the wrist in degrees
  public static final double STOW = 0.0; // Angle for the wrist when stowed in degrees
  public static final double INTAKE = -45.0; // Angle for the wrist when in intake position in degrees
  public static final double BARGE= 45.0; // Angle for the wrist when in barge position in degrees
  public static final double L1 = 30.0; // Angle for the wrist when in level 1 position in degrees
  public static final double L2 = 60.0; // Angle for the wrist when in level 2 position in degrees
  public static final double L3= 90.0; // Angle for the wrist when in level 3 position in degrees
  public static final double L4 = 120.0; // Angle for the wrist when in level 4 position in degreess
    }

  public static final class ClimbConstants {


    }
  }

