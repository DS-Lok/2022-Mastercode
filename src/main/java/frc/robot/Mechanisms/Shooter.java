package frc.robot.Mechanisms;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Systems.Vision;

public class Shooter {
    
    Boolean AUTODUMPING = true;
    WPI_TalonFX m_MasterMotor;
    WPI_TalonFX m_FollowerMotor;
    WPI_TalonSRX m_feeder;

    double kP = 0.15;
    double kI = 0.001;
    double kD = 5;
    double kF = .025;


    public Shooter() {
        m_MasterMotor = new WPI_TalonFX(7);
        m_FollowerMotor = new WPI_TalonFX(8);

        m_MasterMotor.configFactoryDefault();
        m_FollowerMotor.configFactoryDefault();
        

        
        
        m_MasterMotor.configFactoryDefault();   
        m_MasterMotor.config_kP(0, kP);
        m_MasterMotor.config_kI(0, kI);
        m_MasterMotor.config_kD(0, kD);
        m_MasterMotor.config_kF(0, kF);
        m_MasterMotor.configPeakOutputForward(1);
        m_MasterMotor.configPeakOutputReverse(-1);



        m_FollowerMotor.follow(m_MasterMotor);
        m_FollowerMotor.setInverted(true);

     

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
        SmartDashboard.putNumber("RightShooter", m_FollowerMotor.getSelectedSensorVelocity());
        SmartDashboard.putNumber("LeftShooter", m_MasterMotor.getSelectedSensorVelocity());
        SmartDashboard.putBoolean("AUTODUMP", AUTODUMPING);


        if(DISABLECOMMAND){
            AUTODUMPING = !AUTODUMPING;
        }

        if(mode >=0 & BALLCOLOR != ALLIANCE & AUTODUMPING){
            mode = 270;
            
        }

        switch (mode) {
            case 0:
                m_MasterMotor.set(TalonFXControlMode.Velocity, 5550);
                break;

            case 90:
                m_MasterMotor.set(TalonFXControlMode.Velocity, 5750);
                break;
            
            case 180:
                m_MasterMotor.set(TalonFXControlMode.Velocity, 7500);
                break;
            case 270:
                m_MasterMotor.set(ControlMode.PercentOutput, 0.15);
                break;
 
            case -1:
                m_MasterMotor.set(ControlMode.PercentOutput, 0);
                break;

            default:
                m_MasterMotor.set(ControlMode.PercentOutput, 0);
                break;
        }

    }





    public double speedForDistance() {
        //create some sort of table of Distance VS Speed
        
        return 0;

    }
    



}
