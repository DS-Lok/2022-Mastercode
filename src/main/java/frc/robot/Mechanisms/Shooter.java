package frc.robot.Mechanisms;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Systems.Vision;

public class Shooter {
    WPI_TalonFX m_leftMotor;
    WPI_TalonFX m_rightMotor;

    WPI_TalonSRX m_feeder;

    public Shooter() {
        m_leftMotor = new WPI_TalonFX(7);
        m_rightMotor = new WPI_TalonFX(8);

        m_rightMotor.configFactoryDefault();
        m_leftMotor.configFactoryDefault();

       // m_rightMotor.setInverted(true); will probably need to invert one of the motors. I want positive to be shooting forward for both


        m_feeder = new WPI_TalonSRX(13);
    }


    public void feed(Boolean On) {
        if (On = true) {
            m_feeder.set(ControlMode.PercentOutput, .7); //placeholder speed, maybe make staight 1 if I can it
        } else {m_feeder.set(ControlMode.PercentOutput, 0);}
    }

    public void flywheelRev(int mode) { // Modes: 0 = straight against hub, 1 = pin shot, 2 = limelight assisted
        
        switch (mode) {
            case 0:
                m_rightMotor.set(TalonFXControlMode.Velocity, 1640); //all values here are placeholders. Test to find actual velocities
                m_leftMotor.set(TalonFXControlMode.Velocity, 1640);
                break;

            case 1:
                m_leftMotor.set(TalonFXControlMode.Velocity, 1807);
                m_rightMotor.set(TalonFXControlMode.Velocity, 1807);
                break;
            
            case 2:
                m_leftMotor.set(TalonFXControlMode.Velocity, speedForDistance());
                m_rightMotor.set(TalonFXControlMode.Velocity, speedForDistance());
                break;


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