package com.team6560.frc2025.commands;
import com.team6560.frc2025.subsystems.Wrist;
import com.team6560.frc2025.Constants.WristConstants;
import com.team6560.frc2025.subsystems.Wrist.State;
import edu.wpi.first.wpilibj2.command.Command;
import com.team6560.frc2025.ManualControls;

public class WristCommand extends Command {
    final Wrist wrist;
    final ManualControls manualControls;
    public State target_state;
    public String target_name;

    public WristCommand(Wrist wrist, ManualControls manualControls) {
        this.wrist = wrist;
        this.manualControls = manualControls;
    }

    @Override
    public void initialize() {
        wrist.setWristPosition(WristConstants.STOW); // Initialize wrist to stowed position
        target_state = Wrist.State.STOW; // Set initial target state to STOW
    }

    @Override
    public void execute() {

        if(manualControls.shiftedControls()){

            if (manualControls.goToL2()){
    
              target_state = State.S_L2;
    
            } else if(manualControls.goToL4()){
    
              target_state = State.S_L4;
    
            } else if (manualControls.goToStow()) {
              target_state = State.S_STOW;
    
            }
    
          } 
        if (manualControls.goToL1()) {
            target_state = Wrist.State.L1;
        } else if (manualControls.goToL2()) {
            target_state = Wrist.State.L2;
        } else if (manualControls.goToL3()) {
            target_state = Wrist.State.L3;
        } else if (manualControls.goToL4()) {
            target_state = Wrist.State.L4;
        } else if (manualControls.goToStow()) {
            target_state = Wrist.State.STOW;
        } else if (manualControls.goToPickup()) {
            target_state = Wrist.State.INTAKE;
        } else if (manualControls.goToBarge()) {
            target_state = Wrist.State.BARGE;
        } else {
            target_state = Wrist.State.IN_MOTION; // If no specific state is requested, set to IN_MOTION
        }
        double targetAngle;
    switch (target_state) {
        case L1:
            targetAngle = WristConstants.L1;
            break;
        case L2:
            targetAngle = WristConstants.L2;
            break;
        case L3:
            targetAngle = WristConstants.L3;
            break;
        case L4:
            targetAngle = WristConstants.L4;
            break;
        case STOW:
            targetAngle = WristConstants.STOW;
            break;
        case INTAKE:
            targetAngle = WristConstants.INTAKE;
            break;
        case BARGE:
            targetAngle = WristConstants.BARGE;
            break;
        default:
            // Hold current position if no valid state
            targetAngle = wrist.getWristAngle();
            break;
    }

    // Command wrist to move to target position
    wrist.setWristPosition(targetAngle);
    }

    @Override
    public boolean isFinished() {
        return false; // This command runs until interrupted
    }

    @Override
    public void end(boolean interrupted) {
        wrist.terminate(); // Stop the wrist motor
    }
}