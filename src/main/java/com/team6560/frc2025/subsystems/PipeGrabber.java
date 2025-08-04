//Ishaan

package com.team6560.frc2025.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import static com.team6560.frc2025.utility.NetworkTable.NtValueDisplay.ntDispTab;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PipeGrabber extends SubsystemBase {
    private SparkFlex pipeGrabber;

    private static final int GRABBER_MOTOR_ID = 17;

    private double intakeSpeed = 0.2;  //not tuned at all ; arbitrary values
    private double outputSpeed = -0.2;  //not tuned at all ; arbitrary values
    
    private double pipeThreshold = 15.0; //not tuned at all ; arbitrary values
    public PipeGrabber(){
        this.pipeGrabber = new SparkFlex(GRABBER_MOTOR_ID, MotorType.kBrushless);
    }
    public void runPipeGrabberIntake(){
        pipeGrabber.set(intakeSpeed);
    }
    public void runPipeGrabberOutput(){
        pipeGrabber.set(outputSpeed);
    }
    public double getSpeed(){
        return pipeGrabber.get();  //returns on a scale of -1.0 to 1.0
    }
    public double getOutputCurrent(){
         return pipeGrabber.getOutputCurrent();
    }
    public void setIntakeSpeed(double newIntake){
        intakeSpeed = newIntake;
    }
    public void setoutputSpeed(double newOutput){
        outputSpeed = newOutput;
    }
    public void stopPipeGrabber(){
        pipeGrabber.set(0);
    }
    public boolean didGetPipe(){
        if(pipeGrabber.getOutputCurrent()>pipeThreshold){
            return true;
        }
        else{
            return false;
        }
    }
}