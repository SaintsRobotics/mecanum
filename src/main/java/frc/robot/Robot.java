// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 1;
  private static final int kRearLeftChannel = 3;
  private static final int kFrontRightChannel = 2;
  private static final int kRearRightChannel = 4;

  private static final int kControllerChannel = 0;

  private MecanumDrive m_robotDrive;
  private XboxController m_controller;

  WPI_VictorSPX frontLeft = new WPI_VictorSPX(kFrontLeftChannel);
    WPI_VictorSPX rearLeft = new WPI_VictorSPX(kRearLeftChannel);
    WPI_VictorSPX frontRight = new WPI_VictorSPX(kFrontRightChannel);
    WPI_VictorSPX rearRight = new WPI_VictorSPX(kRearRightChannel);

  //final AHRS m_AHRS = new AHRS();

  double m_rot = 0;

  @Override
  public void robotInit() {

    frontLeft.setInverted(true);
    rearLeft.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_controller = new XboxController(kControllerChannel);
  }

  public static double clamp(double a, double b, double c) {return a < b ? b : (a > c ? c : a);}

  @Override
  public void teleopPeriodic() {
    ChassisSpeeds spds0 = new ChassisSpeeds(-m_controller.getLeftY() * 15, m_controller.getLeftX() * 15, m_controller.getRightX());
    ChassisSpeeds speds = ChassisSpeeds.fromFieldRelativeSpeeds(spds0, new Rotation2d(m_rot));
    m_rot += speds.omegaRadiansPerSecond * 0.25;
    m_rot %= Math.PI * 2;
    SmartDashboard.putNumber("r", m_rot);
    m_robotDrive.driveCartesian(clamp(speds.vxMetersPerSecond / 5f, -0.5f, 0.5f), clamp(speds.vyMetersPerSecond / 5f, -0.5, 0.5f), clamp(speds.omegaRadiansPerSecond / 1f, -0.5f, 0.5f));
  }
}
