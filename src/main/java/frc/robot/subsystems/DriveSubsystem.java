// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveSubsystem extends SubsystemBase {
  CANSparkMax m1;
  CANSparkMax m2;
  DifferentialDrive drive;
  public DriveSubsystem() {
    m1 = new CANSparkMax(0, MotorType.kBrushless);
    m2 = new CANSparkMax(1, MotorType.kBrushless);
    drive = new DifferentialDrive(m1, m2);
  }

  public void drive(double x, double y) {
    drive.tankDrive(x,y);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  // public CommandBase exampleMethodCommand() {
  //   // Inline construction of command goes here.
  //   // Subsystem::RunOnce implicitly requires `this` subsystem.
  //   return runOnce(
  //       () -> {
  //         /* one-time action goes here */
  //       });
  // }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  // public boolean exampleCondition() {
  //   // Query some boolean state, such as a digital sensor.
  //   return false;
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

//   @Override
//   public void simulationPeriodic() {
//     // This method will be called once per scheduler run during simulation
//   }
}
