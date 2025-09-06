package com.team6560.frc2025.subsystems;


import com.revrobotics.spark.SparkLowLevel.MotorType;

import com.revrobotics.spark.SparkMax;

import static com.team6560.frc2025.utility.NetworkTable.NtValueDisplay.ntDispTab;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.team6560.frc2025.Constants  ;

public class BallGrabber extends SubsystemBase {
    private SparkMax grabberMotor; 

    private static final int GRABBER_MOTOR_ID = 25;
    private static final double INTAKE_SPEED = -0.3;
    private static final double OUTTAKE_SPEED = 0.7; 

    private static final double MAX_CURRENT_RUNNING = 30; 
    private static final double GAMEPEICE_CURRENT = 20; //  threshold to detect if a ball is present

    final NetworkTable nt = NetworkTableInstance.getDefault().getTable("Ball Grabber");
    final NetworkTableEntry ballDetectedEntry = nt.getEntry("Ball Detected");
    final NetworkTableEntry motorCurrent = nt.getEntry("Ball Grabber Current");

    public BallGrabber() {
        this.grabberMotor = new SparkMax(GRABBER_MOTOR_ID, MotorType.kBrushless);
    }


public void periodic(){
    motorCurrent.setDouble(grabberMotor.getOutputCurrent());
    ballDetectedEntry.setBoolean(getBallDetected());
    if((grabberMotor.getOutputCurrent() > GAMEPEICE_CURRENT && grabberMotor.getOutputCurrent() < MAX_CURRENT_RUNNING)){
        SmartDashboard.setDefaultBoolean(getName() + " Ball Detected", true);
    } else if (grabberMotor.getOutputCurrent() < GAMEPEICE_CURRENT) {
        SmartDashboard.setDefaultBoolean(getName() + " Ball Detected", false);
    }
}
public void runIntakeOuttake(){
    if((grabberMotor.getOutputCurrent() > GAMEPEICE_CURRENT && grabberMotor.getOutputCurrent() < MAX_CURRENT_RUNNING)){
        grabberMotor.set(OUTTAKE_SPEED);
    } 
    else if ((grabberMotor.getOutputCurrent() > GAMEPEICE_CURRENT && grabberMotor.getOutputCurrent() < MAX_CURRENT_RUNNING)){
        grabberMotor.set(INTAKE_SPEED);
    }
    else{
        grabberMotor.set(0.2);
    }
}

public boolean getBallDetected(){
    return grabberMotor.getOutputCurrent() > GAMEPEICE_CURRENT && grabberMotor.getOutputCurrent() < MAX_CURRENT_RUNNING;
}



public void stop(){
    grabberMotor.set(0);
}

public double getMotorVelocity(){
    return grabberMotor.get();
}

public double getDutyCycle() {
    return grabberMotor.get(); 
}
}






