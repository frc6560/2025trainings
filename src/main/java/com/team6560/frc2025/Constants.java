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
    
    //placeholders
    public static int ElevLeftCanID = 1;
    public static int ElevRightCanID = 1;
    public static int ElevLeftEncID = 1;
    public static double ElevSpeed = 0.2;
    
    public static final int TopLimitSwitchID = 1;
    public static final int BotLimitSwitchID = 1;

      public static final class State{
      
        //need to be tested
        public static final double L1ORSTOW = 0;
        public static final double L2 = 0;
        public static final double L3 = 0;
        public static final double L4 = 0;
        public static final double REMOVEBALLL12 = 0;
        public static final double REMOVEBALLL2L3 = 0;
        public static final double SHOOTBALL = 0;
      }
    
  }

  public static final class WristConstants {
  }

  public static final class ClimbConstants {


  }
}