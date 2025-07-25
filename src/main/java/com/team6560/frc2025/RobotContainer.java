package com.team6560.frc2025;

import com.pathplanner.lib.auto.NamedCommands;
import com.team6560.frc2025.Constants.ElevatorConstants;
import com.team6560.frc2025.Constants.OperatorConstants;
// This WILL be broken. Good luck!
import com.team6560.frc2025.commands.BallGrabberCommand;
import com.team6560.frc2025.subsystems.BallGrabber;
import com.team6560.frc2025.subsystems.Wrist;
import com.team6560.frc2025.subsystems.swervedrive.SwerveSubsystem;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.path.TravelingSalesman;
import edu.wpi.first.math.geometry.Rotation2d;

import java.io.File;
import com.frc3481.swervelib.SwerveInputStream;

// took out subsystems + added camera
public class RobotContainer {

  final CommandXboxController driverXbox = new CommandXboxController(0);
  final XboxController firstXbox = new XboxController(0);
  final XboxController secondXbox = new XboxController(1);

  private final ManualControls controls = new ManualControls(firstXbox, secondXbox);

  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),"swerve/falcon"));

  private final BallGrabber ballGrabber;
  private final BallGrabberCommand ballGrabberCommand;

  private final Wrist wrist;


  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(
    drivebase.getSwerveDrive(),
      () -> (Math.pow(driverXbox.getLeftY(), 2)
            * Math.copySign(1, driverXbox.getLeftY())) 
            * -0.9 * ((firstXbox.getLeftTriggerAxis() > 0.25) || (secondXbox.getLeftBumperButton()) ? 0.6 : 1),
      () -> (Math.pow(driverXbox.getLeftX(), 2)
            * Math.copySign(1, driverXbox.getLeftX())) 
            * -0.9 * ((firstXbox.getLeftTriggerAxis() > 0.25) || (secondXbox.getLeftBumperButton()) ? 0.6 : 1))
    .withControllerRotationAxis(() -> 
    driverXbox.getRightX() * driverXbox.getRightX() * Math.copySign(1, driverXbox.getRightX()))
    .deadband(OperatorConstants.DEADBAND)
    .scaleTranslation(0.8)
    .allianceRelativeControl(true);; 

  public RobotContainer() {

    CameraServer.startAutomaticCapture(0);

    
    ballGrabber = new BallGrabber();
    ballGrabberCommand = new BallGrabberCommand(ballGrabber, controls);
    ballGrabber.setDefaultCommand(ballGrabberCommand);
    wrist = new Wrist();
    // wrist.setDefaultCommand(new WristCommand(wrist, controls));
  }
  private void configureBindings() { 

    Command driveFieldOrientedAnglularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);

    drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity);

    driverXbox.start().onTrue((Commands.runOnce(drivebase::zeroNoAprilTagsGyro)));

    driverXbox.b().onTrue((Commands.runOnce(drivebase::resetOdometryToLimelight)));

    // driverXbox.x().onTrue(Commands.runOnce(drivebase::addFakeVisionReading));
    // driverXbox.b().whileTrue(drivebase.driveToPose(new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0))));
    
    //  driverXbox.leftBumper().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());

    // use auto align mechanism
    driverXbox.x().whileTrue(new RunCommand(() -> drivebase.driveToNearestPoseLeft().schedule(), drivebase));

    driverXbox.a().whileTrue(new RunCommand(() -> drivebase.driveToNearestPoseRight().schedule(), drivebase));


  }


  public void resetLLBeforeAuto() {
    drivebase.resetOdometryToLimelight();
  }

  public Command getTestAuto(){
    return drivebase.getAutonomousCommand("TestAuto");
  }

  public Command getHue25Auto() {
    return drivebase.getAutonomousCommand("HueAuto2.5");
  }

  public Command getTaxiAuto() {
    return drivebase.getAutonomousCommand("Taxi Auto");
  }

  
  // don't randomly brake/unbrake chassis
  public void setMotorBrake(boolean brake) {
    // drivebase.setMotorBrake(brake);
  }
  public void resetHeading() {
    // TODO Auto-generated method stub
    this.drivebase.zeroGyro();
  }
}