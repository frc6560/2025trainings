package com.team6560.frc2025.commands;

import com.team6560.frc2025.subsystems.Climb;
// import frc.robot.subsystems.Climb.State;
import com.team6560.frc2025.ManualControls;
import com.team6560.frc2025.Constants.ClimbConstants;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimbCommand extends Command {

    private final Climb climb;
    // State targetState = State.UP;
    private final ManualControls controls;

    public ClimbCommand(Climb climb, ManualControls controls) {
        this.climb = climb;
        this.controls = controls;

        addRequirements(climb);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (controls.getClimbDown()) {
            // climb.state = Climb.State.DOWN;
            climb.movedown();
        } else if (controls.getClimbUp()) {
            // climb.state = Climb.State.UP;
            climb.moveup();
        } else {
             // climb.state = Climb.State.STATIC; continuous motion
            climb.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        climb.stop();
    }

    @Override
    public boolean isFinished() {
        return false; 
    }
}