package com.team6560.frc2025.subsystems;
// CTRE imports
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.sim.CANcoderSimState;
import com.ctre.phoenix6.sim.TalonFXSimState;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
// WPILib (Subsystem & Utilities) imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableInstance.NetworkMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Constants
import com.team6560.frc2025.Constants.WristConstants;

public class Wrist extends SubsystemBase{

    //motor
    private final TalonFX wristMotor;

    //encoder
    private final CANcoder wristEncoder;

    private final NetworkTable ntTable = NetworkTableInstance.getDefault().getTable("Wrist");
    private final NetworkTableEntry ntAngle = ntTable.getEntry("Angle");
    private final NetworkTableEntry ntPosition = ntTable.getEntry("wrist position");
    private final NetworkTableEntry ntTargetPos = ntTable.getEntry("Target angle");

    //target pos
    private double targetPos = 0.0;

    public enum State {
        L1,
        L2,
        L3,
        L4,
        STOW,
        INTAKE,
        BARGE,
        IN_MOTION,
        S_STOW,
        S_L2,
        S_L4
    }

    public Wrist() {
        //initialization
        wristMotor = new TalonFX(WristConstants.M_ID); // Replace with actual CAN ID
        wristEncoder = new CANcoder(WristConstants.Encoder_ID); // Replace with actual CAN ID
        TalonFXConfiguration wristConfig = new TalonFXConfiguration();

        wristConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake; // Set motor to brake mode
        wristConfig.Feedback.RotorToSensorRatio = WristConstants.GEAR_RATIO; //sets gear ration between motor and encoder
        
        Slot0Configs PID_controller = new Slot0Configs();
        PID_controller.kP = WristConstants.PID_KP; // Proportional gain for position control
        PID_controller.kI = WristConstants.PID_KI; // Integral gain for position control
        PID_controller.kD = WristConstants.PID_KD; // Derivative gain for position control
        PID_controller.kG = WristConstants.PID_KG; // Feedforward gain, adjust as needed
        PID_controller.kS = WristConstants.PID_KS; // Static gain, adjust as needed
        
        wristMotor.getConfigurator().apply(PID_controller); // Apply PID gains

        // In Wrist.java constructor:


    }
    @Override
    public void periodic() {
        // Update NetworkTables periodically
        ntAngle.setDouble(getWristAngle());
        ntPosition.setDouble(getWristPosition());
        ntTargetPos.setDouble(targetPos);

    }

    public void setWristPosition(double position) {
        // Set the wrist motor to a specific position
        wristMotor.setControl(new PositionVoltage(position * 360 / WristConstants.GEAR_RATIO)); // Convert angle to encoder position
    }
    public void setWristVelocity(double velocity) {
        // Set the wrist motor to a specific velocity
        wristMotor.setControl(new VelocityVoltage(velocity));
    }
    public double getWristPosition() {
        // Get the current position of the wrist
        return wristMotor.getPosition().getValueAsDouble();
    }
    public double getWristVelocity() {
        // Get the current velocity of the wrist
        return wristEncoder.getVelocity().getValueAsDouble();
    }
    public void setWristBrakeMode(boolean enabled) {
        // Set the wrist motor to brake
    }
    public double getWristAngle() {
        // Get the current angle of the wrist
        return wristEncoder.getPosition().getValueAsDouble() * 360 / WristConstants.GEAR_RATIO; // Convert encoder position to angle in degrees
    }
    public double get_upper_bound() {
        // Get the upper bound of the wrist
        return WristConstants.UPPER_BOUND; // will add later
    }

    public double get_lower_bound() {
        // Get the lower bound of the wrist
        return WristConstants.LOWER_BOUND; // will add later
    }
    public void terminate() {
// stops wrist
        wristMotor.set(0);
    }

    public State getState(){
        // Determine the current state of the wrist based on its position
        double padding = 2.5;
        double angle = getWristAngle();
        
        if (angle - WristConstants.L1 > padding) {
            return State.L1;
        } else if (angle - WristConstants.L2 > padding) {
            return State.L2;
        } else if (angle - WristConstants.L3 > padding) {
            return State.L3;
        } else if (angle - WristConstants.L4 > padding) {
            return State.L4;
        } else if (angle - WristConstants.STOW > padding) {
            return State.STOW;
        } else if (angle - WristConstants.INTAKE > padding) {
            return State.INTAKE;
        } else if (angle - WristConstants.BARGE > padding) {
            return State.BARGE;
        } else if (angle - WristConstants.S_STOW > padding) {
            return State.S_STOW; 
        } else if (angle - WristConstants.S_L2 > padding) {
            return State.S_L2; 
        } else if (angle - WristConstants.S_L4 > padding) {
            return State.S_L4; 
        } else {
            return State.IN_MOTION; // If no specific state is matched, return IN_MOTION
           
        }
    }
    
}
// state machine