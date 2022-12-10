// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 1;
  private static final int kRearLeftChannel = 3;
  private static final int kFrontRightChannel = 2;
  private static final int kRearRightChannel = 4;

  private static final int kControllerChannel = 0;

  private MecanumDrive m_robotDrive;
  private XboxController m_controller;

  @Override
  public void robotInit() {
    WPI_VictorSPX frontLeft = new WPI_VictorSPX(kFrontLeftChannel);
    WPI_VictorSPX rearLeft = new WPI_VictorSPX(kRearLeftChannel);
    WPI_VictorSPX frontRight = new WPI_VictorSPX(kFrontRightChannel);
    WPI_VictorSPX rearRight = new WPI_VictorSPX(kRearRightChannel);

    frontLeft.setInverted(true);
    rearLeft.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_controller = new XboxController(kControllerChannel);
  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.driveCartesian(-m_controller.getLeftY(), m_controller.getLeftX(), m_controller.getRightX());
  }
}
