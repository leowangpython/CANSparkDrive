// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveSubsystem extends SubsystemBase {
  private final CANSparkMax m1;
  private final CANSparkMax m2;
  private final DifferentialDrive drive;
  public DriveSubsystem() {
    m1 = new CANSparkMax(10, MotorType.kBrushless);
    m2 = new CANSparkMax(11, MotorType.kBrushless);
    drive = new DifferentialDrive(m1, m2);
  }

  public void drive(double x, double y) {
    drive.arcadeDrive(y,x);
  }

  @Override
  public void periodic() {}
}
