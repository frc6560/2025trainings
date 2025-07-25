package com.team6560.frc2025.commands;

import edu.wpi.first.wpilibj2.command.Command;
import com.team6560.frc2025.Constants.ElevatorConstants;
import com.team6560.frc2025.subsystems.Elevator;
import com.team6560.frc2025.ManualControls;

public class ElevatorCommand extends Command{

    public Elevator elevator;
    public ManualControls controls;

    public ElevatorCommand(Elevator elevator, ManualControls controls){
        this.elevator = elevator;
        this.controls = controls;
        addRequirements(elevator);
    }   

    public void initialize() {
        elevator.stopElev();
    }

    public void execute() {

        if (elevator.getLimitSwitchTop()) {
            elevator.stopElev();
        }

        else if (elevator.getLimitSwitchBot()) {
            elevator.stopElev();
        }

        if (controls.goToL1()) {
            elevator.setElevatorPosition(ElevatorConstants.State.L1ORSTOW);
        }

        else if (controls.goToL2()) {
            elevator.setElevatorPosition(ElevatorConstants.State.L2);
        }
        
        else if (controls.goToL3()) {
            elevator.setElevatorPosition(ElevatorConstants.State.L3);
        }

        else if (controls.goToL4()) {
            elevator.setElevatorPosition(ElevatorConstants.State.L4);
        }

        else if (controls.shiftedControls()) {
    
            if (controls.goToL2()) {
                elevator.setElevatorPosition(ElevatorConstants.State.REMOVEBALLL12);
            }
            
            else if (controls.goToL3()) {
                elevator.setElevatorPosition(ElevatorConstants.State.REMOVEBALLL2L3);
            }

            else if (controls.goToL4()) {
                elevator.setElevatorPosition(ElevatorConstants.State.SHOOTBALL);
            }
        }
    }

    public void end() {
        elevator.stopElev();
    }

}