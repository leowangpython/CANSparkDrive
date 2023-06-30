// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  private final XboxController controller;
  private final DoubleSupplier y;
  private final DoubleSupplier x;
  private final DriveSubsystem m_DriveSubsystem;
  private final DriveCommand m_DriveCommand;

  public RobotContainer() {
    controller = new XboxController(0);
    y = () -> controller.getLeftY();
    x = () -> controller.getRightX();
    m_DriveSubsystem = new DriveSubsystem();
    m_DriveCommand = new DriveCommand(m_DriveSubsystem, x, y);
    configureBindings();
    m_DriveSubsystem.setDefaultCommand(m_DriveCommand);
  }

  private void configureBindings() {
    // new Trigger(m_DriveSubsystem::exampleCondition)
    //     .onTrue(new DriveCommand(m_DriveSubsystem));

    // // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // // cancelling on release.
    // m_driverController.b().whileTrue(m_DriveSubsystem.exampleMethodCommand());
  }

  public Command getAutonomousCommand() {
    return m_DriveCommand;
  }
}

