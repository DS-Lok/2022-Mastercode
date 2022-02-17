package frc.robot.Mechanisms;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Drivetrain {
    
    private final WPI_TalonFX m_leftMotorOne;
    private final WPI_TalonFX m_leftMotorTwo;
    private final WPI_TalonFX m_leftMotorThree;

    private final WPI_TalonFX m_RightMotorFour;
    private final WPI_TalonFX m_RightMotorFive;
    private final WPI_TalonFX m_RightMotorSix;

    private final MotorControllerGroup left;
    private final MotorControllerGroup right;

    private final DifferentialDrive drive;
    


    

    
    public Drivetrain() {
      m_leftMotorOne = new WPI_TalonFX(1);
      m_leftMotorTwo = new WPI_TalonFX(2);
      m_leftMotorThree = new WPI_TalonFX(3);
  
      m_RightMotorFour = new WPI_TalonFX(4);
      m_RightMotorFive = new WPI_TalonFX(5);
      m_RightMotorSix = new WPI_TalonFX(6);


      m_leftMotorOne.setNeutralMode(NeutralMode.Brake);
      m_leftMotorTwo.setNeutralMode(NeutralMode.Brake);
      m_leftMotorThree.setNeutralMode(NeutralMode.Brake);
      
      m_RightMotorFour.setNeutralMode(NeutralMode.Brake);
      m_RightMotorFive.setNeutralMode(NeutralMode.Brake);
      m_RightMotorSix.setNeutralMode(NeutralMode.Brake);


      right = new MotorControllerGroup(m_RightMotorFour, m_RightMotorFive, m_RightMotorSix);
      left = new MotorControllerGroup(m_leftMotorOne, m_leftMotorTwo, m_leftMotorThree);

      left.setInverted(true);

      drive = new DifferentialDrive(right, left);


     


      }

    @SuppressWarnings("ParameterName")
    public void drive(double speed, double rotation) {

      drive.arcadeDrive(speed, rotation*.65);

    }


    public void target(double angle, int side) { //0 for front of robot (limelight), 1 for back of robot (ball targeting)

      switch (side) {
        case 0:
          
          break;
      
        case 1:

          break;
      }


    }


    public double Speed() {

      double gearRatio = 1; //placeholder
      double averageMotorSpeed = ((m_leftMotorOne.getSelectedSensorVelocity() + m_leftMotorTwo.getSelectedSensorVelocity() + m_leftMotorThree.getSelectedSensorVelocity()) + (m_RightMotorFour.getSelectedSensorVelocity() + m_RightMotorFive.getSelectedSensorVelocity() + m_RightMotorSix.getSelectedSensorVelocity()/6));
      
      double wheelSpeed = averageMotorSpeed/gearRatio; //placeholder


      return wheelSpeed;  
    }





}
