// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

// import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;



/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_drivetrain;
  private XboxController m_xbox;  


   
  



  private final CANSparkMax m_frontLeftMotor = new CANSparkMax(2,MotorType.kBrushed);
  private final CANSparkMax m_rearLeftMotor = new CANSparkMax(3,MotorType.kBrushed);

  private final CANSparkMax m_frontRightMotor = new CANSparkMax(4,MotorType.kBrushed);
  private final CANSparkMax m_rearRightMotor = new CANSparkMax(5,MotorType.kBrushed);

  
  

  // public void power(double speed) {
  //   m_frontLeftMotor.set(speed);
  //   m_rearLeftMotor.set(speed);

  //   m_frontRightMotor.set(-speed);
  //   m_rearRightMotor.set(speed);
    
   
  // }

  
  @Override
  public void robotInit() {
    SendableRegistry.addChild(m_drivetrain, m_frontLeftMotor);
    SendableRegistry.addChild(m_drivetrain, m_frontRightMotor);
    SendableRegistry.addChild(m_drivetrain, m_rearRightMotor);
    SendableRegistry.addChild(m_drivetrain, m_rearLeftMotor);




    
  
    m_rearLeftMotor.setSmartCurrentLimit(80);
    m_frontLeftMotor.setSmartCurrentLimit(80);
    m_rearRightMotor.setSmartCurrentLimit(80);
    m_frontRightMotor.setSmartCurrentLimit(80);


    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_frontLeftMotor.setInverted(true);
    m_rearLeftMotor.setInverted(true);


    // m_frontLeftMotor.follow(m_rearLeftMotor);
    // m_frontRightMotor.follow(m_rearRightMotor);

    m_rearLeftMotor.follow(m_frontLeftMotor);
    m_rearRightMotor.follow(m_frontRightMotor);

    m_drivetrain = new DifferentialDrive(m_frontLeftMotor,m_frontRightMotor);
    
    

    m_drivetrain = new DifferentialDrive(m_frontLeftMotor::set, m_frontRightMotor::set);
    m_xbox = new XboxController(0);

    
    
  }
  public void DriveTrain(){
    m_drivetrain.arcadeDrive(-m_xbox.getRawAxis(1), -m_xbox.getRawAxis(4), false);
  }
  
  // public void Giro(){
  //   m_drivetrain.tankDrive(m_xbox.getRawAxis(4), -m_xbox.getRawAxis(4), false);
  // }

  @Override
  public void teleopPeriodic() {
    // m_rearLeftMotor.set(0.5);
    // m_frontLeftMotor.set(0.5);
    // m_rearRightMotor.set(0.0);
    // m_frontRightMotor.set(0.0);


    // MotorSafety.checkMotors();
    DriveTrain();
    

    if(m_xbox.getStartButtonPressed()) {
      m_rearLeftMotor.stopMotor();
      m_frontLeftMotor.stopMotor();
      m_rearRightMotor.stopMotor();
      m_frontRightMotor.stopMotor();
    }
  }
}
