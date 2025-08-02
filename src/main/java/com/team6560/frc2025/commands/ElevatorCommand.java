package com.team6560.frc2025.commands;

import edu.wpi.first.wpilibj2.command.Command;
import com.team6560.frc2025.Constants.ElevatorConstants;
import com.team6560.frc2025.subsystems.Elevator;
import com.team6560.frc2025.ManualControls;
import com.team6560.frc2025.subsystems.Elevator.State;

public class ElevatorCommand extends Command{

    public Elevator elevator;
    public ManualControls controls;

    private State targetState;

    public ElevatorCommand(Elevator elevator, ManualControls controls){
        this.elevator = elevator;
        this.controls = controls;
        addRequirements(elevator);

    }   

    public void initialize() {
        elevator.stopElev();
        elevator.setElevatorPosition(ElevatorConstants.State.L1ORSTOW);

    }

    public void execute() {

        if (elevator.getLimitSwitchTop()) {
            elevator.stopElev();
        }

        else if (elevator.getLimitSwitchBot()) {
            elevator.stopElev();
        }

        if (controls.goToL1()) {
            targetState = State.L1ORSTOW;
        }

        else if (controls.goToL2()) {
            targetState = State.L2;
        }
        
        else if (controls.goToL3()) {
            targetState = State.L3;
        }

        else if (controls.goToL4()) {
            targetState = State.L4;
        }

        else if (controls.shiftedControls()) {
    
            if (controls.goToL2()) {
                targetState = State.REMOVEBALLL12;
            }
            
            else if (controls.goToL3()) {
                targetState = State.REMOVEBALLL2L3;
            }

            else if (controls.goToL4()) {
                targetState = State.SHOOTBALL;
            }
        }
        double targetRotations = 0;

        if (targetState == State.L1ORSTOW) {
            targetRotations = (ElevatorConstants.State.L1ORSTOW);
        }
        else if (targetState == State.L2) {
            targetRotations = (ElevatorConstants.State.L2);
        }
        else if (targetState == State.L3) {
            targetRotations = (ElevatorConstants.State.L3);
        }
        else if (targetState == State.L4) {
            targetRotations = (ElevatorConstants.State.L4);
        }
        else if (targetState == State.REMOVEBALLL12) {
            targetRotations = (ElevatorConstants.State.REMOVEBALLL12);
        }
        else if (targetState == State.REMOVEBALLL2L3) {
            targetRotations = (ElevatorConstants.State.REMOVEBALLL2L3);
        }
        else if (targetState == State.SHOOTBALL) {
            targetRotations = (ElevatorConstants.State.SHOOTBALL);
        }
        
        elevator.setElevatorPosition(targetRotations);

    }

    public boolean isFinished() {
        return false;
    }


    public void end() {
        elevator.stopElev();
    }

}