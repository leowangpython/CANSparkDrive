// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final DriveSubsystem m_DriveSubsystem;
  

  private final DoubleSupplier x;
  private final DoubleSupplier y;


  public DriveCommand(DriveSubsystem subsystem, DoubleSupplier x, DoubleSupplier y) {
    m_DriveSubsystem = subsystem;
    this.x = x;
    this.y = y;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_DriveSubsystem.drive(x.getAsDouble(), y.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
