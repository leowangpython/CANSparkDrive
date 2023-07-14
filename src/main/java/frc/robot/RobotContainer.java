// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DriveCommand;
import frc.robot.commands.VisionCommand;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import org.photonvision.PhotonCamera;

public class RobotContainer {
  private final XboxController controller;
  private final DoubleSupplier y;
  private final DoubleSupplier x;
  private final DriveSubsystem m_DriveSubsystem;
  private final DriveCommand m_DriveCommand;

  private final NetworkTableInstance inst;
  private final PhotonCamera camera;
  private final VisionCommand m_VisionCommand;
  private final VisionSubsystem m_VisionSubsystem;

  public RobotContainer() {
    controller = new XboxController(0);
    y = () -> controller.getLeftY();
    x = () -> controller.getRightX();
    m_DriveSubsystem = new DriveSubsystem();
    m_DriveCommand = new DriveCommand(m_DriveSubsystem, x, y);
    m_DriveSubsystem.setDefaultCommand(m_DriveCommand);

    inst = NetworkTableInstance.getDefault();
    inst.startServer();

    camera = new PhotonCamera("photonvision");
    m_VisionSubsystem = new VisionSubsystem();
    m_VisionCommand = new VisionCommand(m_VisionSubsystem, camera);
    m_VisionSubsystem.setDefaultCommand(m_VisionCommand);
  }

  public Command getAutonomousCommand() {
    return m_DriveCommand;
  }
}

