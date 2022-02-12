// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Mechanisms.Collector;
import frc.robot.Mechanisms.Drivetrain;
import frc.robot.Mechanisms.Indexer;
import frc.robot.Mechanisms.Shooter;
import frc.robot.Systems.Auto;
import frc.robot.Systems.Vision;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();


  //public final Drivetrain m_Drive = new Drivetrain();
  //private final Collector m_Collector = new Collector();
  //private final Shooter m_shooter = new Shooter();
  //private final Indexer m_indexer = new Indexer();

   WPI_TalonFX m_leftMotorOne;
   WPI_TalonFX m_leftMotorTwo;
   WPI_TalonFX m_leftMotorThree;

   WPI_TalonFX m_RightMotorFour;
   WPI_TalonFX m_RightMotorFive;
   WPI_TalonFX m_RightMotorSix;
  
   WPI_TalonFX m_Evan;

  private XboxController m_DriveController;
  private XboxController m_OperatController;


  static int autoSection;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    

    m_DriveController = new XboxController(0);
    m_OperatController = new XboxController(1);
    
    m_leftMotorOne = new WPI_TalonFX(1);
    m_leftMotorTwo = new WPI_TalonFX(2);
    m_leftMotorThree = new WPI_TalonFX(3);

    m_RightMotorFour = new WPI_TalonFX(4);
    m_RightMotorFive = new WPI_TalonFX(5);
    m_RightMotorSix = new WPI_TalonFX(6);

    m_Evan = new WPI_TalonFX(7);

    m_leftMotorOne.setNeutralMode(NeutralMode.Coast);
    m_leftMotorTwo.setNeutralMode(NeutralMode.Coast);
    m_leftMotorThree.setNeutralMode(NeutralMode.Coast);
    
    m_RightMotorFour.setNeutralMode(NeutralMode.Coast);
    m_RightMotorFive.setNeutralMode(NeutralMode.Coast);
    m_RightMotorSix.setNeutralMode(NeutralMode.Coast);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    autoSection = 0;



  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }

    switch (autoSection) {
      case 0:
        //m_Drive.drive(Auto.readSequence("xInputTestAuto"), Auto.readSequence("yInputTestAuto"));
        if (Auto.readSequence("xInputTestAuto") == 9999) {autoSection = 1;}
        break;
      
      case 1:
        //m_Drive.target(Vision.AngleFromTarget(), 0);
      //  m_shooter.flywheelRev(2);
      //  if (m_indexer.empty()) {autoSection = 2;}
        break;

      default:
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
 //   m_Collector.dropped(false);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //m_Drive.drive(m_DriveController.getLeftY(), m_DriveController.getRightX());

    if (m_DriveController.getAButton()) {
      m_leftMotorOne.set(ControlMode.PercentOutput, .3);
    } else if (!m_DriveController.getAButton()) {
      m_leftMotorOne.set(ControlMode.PercentOutput, 0);
    }



    if (m_DriveController.getBButton()) {
      m_leftMotorTwo.set(ControlMode.PercentOutput, .3);
    } else if (!m_DriveController.getBButton()) {
      m_leftMotorTwo.set(ControlMode.PercentOutput, 0);
    }


    if (m_DriveController.getXButton()) {
      m_leftMotorThree.set(ControlMode.PercentOutput, .3);
    } else if (!m_DriveController.getXButton()) {
      m_leftMotorThree.set(ControlMode.PercentOutput, 0);
    }



    if (m_DriveController.getYButton()) {
      m_RightMotorFour.set(ControlMode.PercentOutput, .3);
    } else if (!m_DriveController.getYButton()) {
      m_RightMotorFour.set(ControlMode.PercentOutput, 0);
    }

    if (m_DriveController.getLeftBumper()) {
      m_RightMotorFive.set(ControlMode.PercentOutput, .3);
    } else if (!m_DriveController.getLeftBumper()) {
      m_RightMotorFive.set(ControlMode.PercentOutput, 0);
    }

    if (m_DriveController.getRightBumper()) {
      m_RightMotorSix.set(ControlMode.PercentOutput, .3);
    } else if (!m_DriveController.getRightBumper()) {
      m_RightMotorSix.set(ControlMode.PercentOutput, 0);
    }

/*
    if (m_DriveController.getRightBumper()) {
      m_Evan.set(ControlMode.PercentOutput, .3);
    } else if (!m_DriveController.getRightBumper()) {
      m_Evan.set(ControlMode.PercentOutput, 0);
    }*/
   // m_Collector.dropped(m_OperatController.getAButton()); //placeholder Button
 //   m_Collector.run(m_OperatController.getBButton()); //placeholder Button

  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    
    if(SmartDashboard.getString("AutoName", "") != ""){
      Auto.recordSequence(SmartDashboard.getString("AutoName", "") + "X", m_DriveController.getLeftY());
      Auto.recordSequence(SmartDashboard.getString("AutoName", "") + "Y", m_DriveController.getRightX());
    }
    
  }
}
