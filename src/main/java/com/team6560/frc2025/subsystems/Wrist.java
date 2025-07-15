package com.team6560.frc2025.subsystems;
// CTRE imports
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Angle;
// WPILib (Subsystem & Utilities) imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Constants
import com.team6560.frc2025.Constants.WristConstants;

public class Wrist extends SubsystemBase{

    //motor
    private final TalonFX wristMotor;

    //encoder
    private final CANcoder wristEncoder;


    public Wrist() {
        //initialization
    }
    
    public void setWristPosition(double position) {
        // Set the wrist motor to a specific position
        wristMotor.setControl(new PositionVoltage(position));
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
        return wristEncoder.getPosition().getValueAsDouble() * 360.0;
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
    }
        
   
}
