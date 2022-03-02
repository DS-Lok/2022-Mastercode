// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PneumaticsBase;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Mechanisms.Climb;
import frc.robot.Mechanisms.Collector;
import frc.robot.Mechanisms.Compress;
import frc.robot.Mechanisms.Drivetrain;
import frc.robot.Mechanisms.Indexer;
import frc.robot.Mechanisms.Shooter;
import frc.robot.Systems.Auto;
import frc.robot.Systems.Vision;


public class Robot extends TimedRobot {
  String teamColor;


  private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(14, PneumaticsModuleType.REVPH, 0, 1);
  private final DoubleSolenoid m_climbSolenoid = new DoubleSolenoid(14, PneumaticsModuleType.REVPH, 5, 4);
   

  public Drivetrain m_Drive = new Drivetrain();
  public Collector m_Collector = new Collector();
  public Indexer m_Indexer = new Indexer();
  public Compress m_Compress = new Compress();
  public Shooter m_Shooter = new Shooter();
  public Climb m_Climb = new Climb();
  public Vision m_Vision = new Vision();

  Compressor m_Compy;

  private XboxController m_DriveController;
  private XboxController m_OperatController;

  int ShootMode;
  Boolean Shoot;
  Boolean TAKE;
  static int autoSection;
  String BALLCOLOR;

  String ALLIANCE;


  @Override
  public void robotInit() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);;
    //teamColor = DriverStation.getAlliance().name();
    //SmartDashboard.putString("Team", teamColor);

    SmartDashboard.putNumber("Hl", 0);
    SmartDashboard.putNumber("Sl", 3);
    SmartDashboard.putNumber("Vl", 115);

    SmartDashboard.putNumber("Hh", 10);
    SmartDashboard.putNumber("Sh", 255);
    SmartDashboard.putNumber("Vh", 255);



    m_DriveController = new XboxController(0);
    m_OperatController = new XboxController(1);
    
    
    m_climbSolenoid.set(Value.kReverse);
    m_doubleSolenoid.set(Value.kReverse);
    m_Compy = new Compressor(14, PneumaticsModuleType.REVPH);  
    //Climb.initClimb(false);
  }


  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    autoSection = 0;



  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
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

  
  @Override
  public void teleopInit() {
    m_Collector.dropped(false, false, m_doubleSolenoid);
    ALLIANCE = DriverStation.getAlliance().name();
    SmartDashboard.putString("Alliance", ALLIANCE);
    m_Indexer.setIndex();


    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0);
  }

  @Override
  public void teleopPeriodic() {
    
  //Driver
  m_Drive.drive(m_DriveController.getLeftY(), m_DriveController.getRightX());
  m_Drive.brake(m_DriveController.getAButton());
  m_Drive.targetLime(m_DriveController.getLeftTriggerAxis() > .5);
  m_Vision.enableLimelight(m_DriveController.getLeftTriggerAxis() > .5);
  if(m_Indexer.getIndexStatus()) m_DriveController.setRumble(RumbleType.kLeftRumble, .1);
  if(m_DriveController.getYButtonReleased()) m_Indexer.setIndex();




  //Operator
  m_Shooter.flywheelRev(m_OperatController.getPOV(), BALLCOLOR, ALLIANCE, m_OperatController.getXButtonReleased(), m_OperatController);
  if (m_OperatController.getRightTriggerAxis() >= .5)Shoot = true;
  else Shoot = false;
  if (m_OperatController.getLeftTriggerAxis() >= .5)TAKE = true;
  else TAKE = false;
  m_Shooter.feed(Shoot, TAKE);

 m_Indexer.index();
 m_Indexer.COLLECT(m_OperatController.getBButton() || Shoot, m_OperatController.getAButton());
 BALLCOLOR = m_Indexer.ColorSensor();

  m_Collector.COLLECT(m_OperatController.getAButton(), m_OperatController.getBButton());
  m_Collector.dropped(m_OperatController.getBButton(), m_OperatController.getAButton(), m_doubleSolenoid);
  
  m_Climb.runWinch(m_OperatController.getLeftY());
  m_Climb.activatePiston(m_OperatController.getRawButtonReleased(7), m_climbSolenoid);
  m_Climb.readEncoder();


  m_Compress.run(m_Compy);

SmartDashboard.putString("Solenoid Value", m_doubleSolenoid.get().name());
  }

 
  @Override
  public void disabledInit() {}


  @Override
  public void disabledPeriodic() {}


  @Override
  public void testInit() {}


  @Override
  public void testPeriodic() {
    
   

  }

}
