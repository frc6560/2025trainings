package com.team6560.frc2025.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;

import com.team6560.frc2025.Constants.ElevatorConstants;
import com.team6560.frc2025.RobotContainer;

public class Elevator extends SubsystemBase{

    public final TalonFX ElevLeft;
    public final TalonFX ElevRight;

    public final DigitalInput topLimitSwitch;
    private final DigitalInput botLimitSwitch;

    private double targetPos = 0;

    public Elevator() {

        this.ElevLeft = new TalonFX(ElevatorConstants.ElevLeftCanID, "Canivore");
        this.ElevRight = new TalonFX(ElevatorConstants.ElevRightCanID, "Canivore");

        this.topLimitSwitch = new DigitalInput(ElevatorConstants.TopLimitSwitchID);
        this.botLimitSwitch = new DigitalInput(ElevatorConstants.BotLimitSwitchID);
        Slot0Configs elevatorPID = new Slot0Configs();
        
        elevatorPID.kP = 0;
        elevatorPID.kI = 0;
        elevatorPID.kD = 0;

        TalonFXConfiguration config = new TalonFXConfiguration();

        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        ElevLeft.getConfigurator().apply(config.withSlot0(elevatorPID));
        ElevRight.getConfigurator().apply(config.withSlot0(elevatorPID));
    }

    public void setElevatorPosition(double targetRotations){
        this.targetPos = targetRotations;

        final PositionVoltage m_request = new PositionVoltage(targetRotations);

        ElevLeft.setControl(m_request);
        ElevRight.setControl(m_request);
    }

    public void stopElev() {
        ElevLeft.stopMotor();
        ElevRight.stopMotor();
    }

    public boolean getLimitSwitchTop() {
        return topLimitSwitch.get();
    }

    public boolean getLImitSwitchBot() {
        return botLimitSwitch.get();
    }
}