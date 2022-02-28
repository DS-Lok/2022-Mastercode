package frc.robot.Mechanisms;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Systems.Vision;

public class Shooter {
    
    Boolean AUTODUMPING = true;
    WPI_TalonFX m_leftMotor;
    WPI_TalonFX m_rightMotor;
    TalonFXConfiguration configs;
    WPI_TalonSRX m_feeder;

    double kP = 0.00015;
    double kI = 0;
    double kD = 0;
    double kF = .025;


    public Shooter() {
        m_leftMotor = new WPI_TalonFX(7);
        m_rightMotor = new WPI_TalonFX(8);

        m_rightMotor.configFactoryDefault();
        m_leftMotor.configFactoryDefault();

        
        

        configs = new TalonFXConfiguration();

       

        configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;

        m_leftMotor.configAllSettings(configs);
        m_rightMotor.configAllSettings(configs);


           
        m_leftMotor.config_kP(0, kP);
        m_leftMotor.config_kI(0, kI);
        m_leftMotor.config_kD(0, kD);
        m_leftMotor.config_kF(0, kF);

        m_rightMotor.config_kP(0, kP);
        m_rightMotor.config_kI(0, kI);
        m_rightMotor.config_kD(0, kD);
        m_rightMotor.config_kF(0, kF);
        m_rightMotor.setInverted(true);

     

        //m_leftMotor.follow(m_rightMotor);

       // m_rightMotor.setInverted(true); will probably need to invert one of the motors. I want positive to be shooting forward for both


        m_feeder = new WPI_TalonSRX(12);
        m_feeder.configFactoryDefault();
        m_feeder.setInverted(true);
    }







    public void feed(Boolean On, Boolean TAKE) {
        if (On == true) {
            m_feeder.set(ControlMode.PercentOutput, .7); //placeholder speed, maybe make staight 1 if I can it
        } 
        else if(TAKE) m_feeder.set(ControlMode.PercentOutput, -.6);
        else {m_feeder.set(ControlMode.PercentOutput, 0);}
    }





    public void flywheelRev(int mode, String BALLCOLOR, String ALLIANCE, Boolean DISABLECOMMAND) { // Modes: 0 = straight against hub, 1 = pin shot, 2 = limelight assisted
        SmartDashboard.putNumber("RightShooter", m_rightMotor.getSelectedSensorVelocity());
        SmartDashboard.putNumber("LeftShooter", m_leftMotor.getSelectedSensorVelocity());
        SmartDashboard.putBoolean("AUTODUMP", AUTODUMPING);


        if(DISABLECOMMAND){
            AUTODUMPING = !AUTODUMPING;
        }

        if((BALLCOLOR != ALLIANCE && BALLCOLOR != "Unimportant") && AUTODUMPING){
            feed(true, false);
            mode = 600;
            
        }

        switch (mode) {
            case 0:
                m_rightMotor.set(TalonFXControlMode.PercentOutput, .35); //all values here are placeholders. Test to find actual velocities
                m_leftMotor.set(TalonFXControlMode.PercentOutput, .35);
                break;

            case 90:
                m_leftMotor.set(TalonFXControlMode.Velocity, 1807);
                m_rightMotor.set(TalonFXControlMode.Velocity, 1807);
                break;
            
            case 180:
                //m_leftMotor.set(TalonFXControlMode.Velocity, speedForDistance());
                //m_rightMotor.set(TalonFXControlMode.Velocity, speedForDistance());

                m_rightMotor.set(TalonFXControlMode.PercentOutput, .64); //all values here are placeholders. Test to find actual velocities
                m_leftMotor.set(TalonFXControlMode.PercentOutput, .64);
                break;
            case 270:
                m_leftMotor.set(TalonFXControlMode.Velocity,500);
                m_rightMotor.set(TalonFXControlMode.Velocity, 500);
                break;
 
            case -1:
                m_rightMotor.set(ControlMode.PercentOutput, 0);
                m_leftMotor.set(ControlMode.PercentOutput, 0);
                break;

            case 600:
            m_rightMotor.set(ControlMode.PercentOutput, .18);
            m_leftMotor.set(ControlMode.PercentOutput, .18);

            default:
                m_rightMotor.set(ControlMode.PercentOutput, 0);
                m_leftMotor.set(ControlMode.PercentOutput, 0);
                break;
        }

    }





    public double speedForDistance() {
        //create some sort of table of Distance VS Speed
        
        return 0;

    }
    



}
