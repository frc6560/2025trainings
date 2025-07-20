package com.team6560.frc2025.subsystems;


import com.revrobotics.spark.SparkLowLevel.MotorType;

import com.revrobotics.spark.SparkMax;

import static com.team6560.frc2025.utility.NetworkTable.NtValueDisplay.ntDispTab; 


import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.team6560.frc2025.Constants;

public class BallGrabber extends SubsystemBase {
    private SparkMax grabberMotor; 

    private static final int GRABBER_MOTOR_ID = 9;
    private static final double INTAKE_SPEED = 1;
    private static final double OUTTAKE_SPEED = -1; 

    private static final double MAX_CURRENT_RUNNING = 30; 
    public BallGrabber() {
        this.grabberMotor = new SparkMax(GRABBER_MOTOR_ID, MotorType.kBrushless);
        ntDispTab("Ball Grabber")
            .add("Ball Grabber Current", () -> grabberMotor.getOutputCurrent())
            .add("Ball Grabber Voltage", () -> grabberMotor.getBusVoltage())
            .add("Ball Grabber Speed", () -> grabberMotor.get())
            .add("Ball Grabber Duty Cycle", () -> getMotorVelocity());


}


public void runIntake(){
if (grabberMotor.getOutputCurrent() < MAX_CURRENT_RUNNING) {
    grabberMotor.set(INTAKE_SPEED);
} else {
    grabberMotor.set(0);
}
}

public void runOuttake(){
    if (grabberMotor.getOutputCurrent() < MAX_CURRENT_RUNNING) {
        grabberMotor.set(OUTTAKE_SPEED);
    } else {
        grabberMotor.set(0);
    }
    grabberMotor.set(OUTTAKE_SPEED);
}

public void stop(){
    grabberMotor.set(0);
}

public double getMotorVelocity(){
    return grabberMotor.get();
}
}






