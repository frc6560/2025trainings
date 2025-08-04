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
        System.out.println("Initialize elevator");
    }

    public void execute() {

        if (elevator.getLimitSwitchTop()) {
            elevator.stopElev();
        }

        else if (elevator.getLimitSwitchBot()) {
            elevator.stopElev();
            System.out.println("Bottom Limit Switch");
        }

        if (controls.shiftedControls()) {
            System.out.println("ShiftedControls");

    
            if (controls.goToL2()) {
                targetState = State.REMOVEBALLL2;
                System.out.println("L1/2 Ball Removal");
            }
            
            else if (controls.goToL3()) {
                targetState = State.REMOVEBALLL3;
                System.out.println("L2/3 Ball Removal");
            }

            else if (controls.goToL4()) {
                targetState = State.SHOOTBALL;
                System.out.println("Shoot Ball");
            }
        }

        else {

            if (controls.goToL1()) {
                targetState = State.L1ORSTOW;
                System.out.println("L1");
            }
    
            else if (controls.goToL2()) {
                targetState = State.L2;
                System.out.println("L2");
            }
            
            else if (controls.goToL3()) {
                targetState = State.L3;
                System.out.println("L3");
            }
    
            else if (controls.goToL4()) {
                targetState = State.L4;
                System.out.println("L4");
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
        else if (targetState == State.REMOVEBALLL2) {
            targetRotations = (ElevatorConstants.State.REMOVEBALLL2);
        }
        else if (targetState == State.REMOVEBALLL3) {
            targetRotations = (ElevatorConstants.State.REMOVEBALLL3);
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