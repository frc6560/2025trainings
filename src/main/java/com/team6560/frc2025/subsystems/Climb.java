package com.team6560.frc2025.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.team6560.frc2025.Constants.ClimbConstants;
import com.team6560.frc2025.ManualControls;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
    
    // public enum State {
    //     UP,
    //     DOWN,
    //     STATIC
    // };

    
    private TalonFX motor1;
    private TalonFX motor2;

    
    private CANcoder absoluteEncoder;
    private final double initialEncoderPos = 0.25;
    private TalonFXConfiguration fxConfig;

    private static final double CLIMB_UP_PERCENT = 0.75;
    private static final double CLIMB_DOWN_PERCENT = 0.75;

    private final NetworkTable ntTable = NetworkTableInstance.getDefault().getTable("Climb");
    private final NetworkTableEntry ntPos = ntTable.getEntry("Position");
    private final NetworkTableEntry ntUseSoftlimits = ntTable.getEntry("ntUseSoftlimits");

    // public State state;

    public Climb(ManualControls controls) { 

        this.motor1 = new TalonFX(ClimbConstants.MOTOR_1_ID, "Canivore");
        this.motor2 = new TalonFX(ClimbConstants.MOTOR_2_ID, "Canivore");
        this.absoluteEncoder = new CANcoder(ClimbConstants.CANCODER_ID, "Canivore");

        this.fxConfig = new TalonFXConfiguration(); 
        fxConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
 
        motor1.getConfigurator().apply(fxConfig);
        motor2.getConfigurator().apply(fxConfig);
        ntPos.setDouble(this.getEncoderPos());
        ntUseSoftlimits.setBoolean(true);
    }

    @Override
    public void periodic() {
        ntPos.setDouble(this.getEncoderPos());
    }

    public void resetEncoderPos() {
        this.motor1.setPosition(0);
        this.motor2.setPosition(0);
        this.absoluteEncoder.setPosition(0);
    }

    public double getMotor1Pos() {
        return this.motor1.getPosition().getValueAsDouble();
    }

    public double getMotor2Pos() { // reversed
        return -this.motor2.getPosition().getValueAsDouble();
    }

    public double getEncoderPos() {
        return this.absoluteEncoder.getAbsolutePosition().getValueAsDouble();
    }

    public double getSpeed() {
        return this.motor1.get();
    }

    public void stop() {
        this.motor1.set(0);
        this.motor2.set(0);
    }

    public void movedown() {
        if (!this.ntUseSoftlimits.getBoolean(true) || this.getEncoderPos() > ClimbConstants.LOWER_SOFT_BOUND) {
            this.motor1.set(-CLIMB_DOWN_PERCENT);
            this.motor2.set(CLIMB_DOWN_PERCENT); } else {
                stop();
            }
    }

    public void moveup() {
        if (!this.ntUseSoftlimits.getBoolean(true) || this.getEncoderPos() < ClimbConstants.UPPER_SOFT_BOUND) {
            this.motor1.set(CLIMB_UP_PERCENT);
            this.motor2.set(-CLIMB_UP_PERCENT); } else {
                stop();
                        }
    }
}