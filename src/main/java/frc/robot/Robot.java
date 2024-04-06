package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class Robot extends TimedRobot {
  
 //movimento do chassi lado esquerdo
  private final CANSparkMax m_frontLeftMotor = new CANSparkMax(2,MotorType.kBrushed);
  private final CANSparkMax m_rearLeftMotor = new CANSparkMax(3,MotorType.kBrushed);
  
//movimento do chassi lado direito
  private final CANSparkMax m_frontRightMotor = new CANSparkMax(4,MotorType.kBrushed);
  private final CANSparkMax m_rearRightMotor = new CANSparkMax(5,MotorType.kBrushed);

  // lançadores lado esquerdo
  private final CANSparkMax lancador_1 = new CANSparkMax(6,MotorType.kBrushed);
  private final CANSparkMax lancador_2 = new CANSparkMax(7,MotorType.kBrushed);
  // lançadores lado direito
  private final CANSparkMax lancador_3 = new CANSparkMax(8, MotorType.kBrushed);
  private final CANSparkMax lancador_4 = new CANSparkMax(9, MotorType.kBrushed);
  
  DigitalInput fimdecurso = new DigitalInput(0);
  // conjunto de controladores 
  private DifferentialDrive drive = new DifferentialDrive(m_frontLeftMotor::set, m_frontRightMotor::set);

  //tempo para o autonomo
  private double startTime;

  // Variável do controle do piloto
  private XboxController controle_1 = new XboxController(0);  
  private XboxController controle_2 = new XboxController(1);

  public void DriveRobot(){
   m_frontLeftMotor.restoreFactoryDefaults();
   m_frontRightMotor.restoreFactoryDefaults();
   m_rearLeftMotor.restoreFactoryDefaults();
   m_rearRightMotor.restoreFactoryDefaults();

   m_rearLeftMotor.follow(m_frontLeftMotor);
   m_rearRightMotor.follow(m_frontRightMotor);
  }
  @Override
  public void robotInit() {
   DriveRobot();
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
   lancador_1.set(-0.40);
   lancador_2.set(-0.40);
   lancador_3.set(0.40);
   lancador_4.set(0.40);

   Timer.delay(1);
   lancador_1.set(0);
   lancador_2.set(1);
   lancador_3.set(0);
   lancador_4.set(-1);

   Timer.delay(1);
   lancador_1.set(0.8);
   lancador_2.set(1);
   lancador_3.set(-0.8);
   lancador_4.set(-1);

   Timer.delay(1.2);
   drive.arcadeDrive(0, 0.5);
   
   Timer.delay(2);

   m_frontLeftMotor.stopMotor();
   m_frontRightMotor.stopMotor();
   lancador_1.stopMotor();
   lancador_2.stopMotor();
   lancador_3.stopMotor();
   lancador_4.stopMotor();
  }

  @Override
  public void autonomousPeriodic() {
   
      


   
   
    // if (Timer.getFPGATimestamp() < 2){
    // drive.arcadeDrive(0, 0.5);
    //} else{
   //   drive.stopMotor();
   //   }
  }
  @Override
  public void teleopInit() {

  }

  public void lancar(){
    if (controle_2.getRawButton(5)){
     lancador_1.set(0.8);
     lancador_2.set(1);
     lancador_3.set(-0.8);
     lancador_4.set(-1);
    } else if (controle_2.getRawButton(1)){
     lancador_1.setIdleMode(IdleMode.kCoast);
     lancador_2.setIdleMode(IdleMode.kCoast);
     lancador_3.setIdleMode(IdleMode.kCoast);
     lancador_4.setIdleMode(IdleMode.kCoast);

     lancador_1.set(0);
     lancador_2.set(1);
     lancador_3.set(0);
     lancador_4.set(-1);
    } else if(controle_2.getRawButton(2)){
     lancador_1.setIdleMode(IdleMode.kBrake);
     lancador_2.setIdleMode(IdleMode.kBrake);
     lancador_3.setIdleMode(IdleMode.kBrake);
     lancador_4.setIdleMode(IdleMode.kBrake);

     lancador_1.set(-0.40);
     lancador_2.set(-0.40);
     lancador_3.set(0.40);
     lancador_4.set(0.40);
   } else{
     lancador_1.stopMotor();
     lancador_2.stopMotor();
     lancador_3.stopMotor();
     lancador_4.stopMotor();
    }
   }

  public void aviso(){
    if (fimdecurso.get() == false){
      System.out.println("tem nota aqui");
    }
  }


  @Override
  public void teleopPeriodic() {
    drive.arcadeDrive(-controle_1.getRawAxis(4),controle_1.getRawAxis(1));
    
    lancar();
    aviso();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    m_frontLeftMotor.setIdleMode(IdleMode.kBrake);
    m_rearLeftMotor.setIdleMode(IdleMode.kBrake);
    m_frontRightMotor.setIdleMode(IdleMode.kBrake);
    m_rearRightMotor.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}