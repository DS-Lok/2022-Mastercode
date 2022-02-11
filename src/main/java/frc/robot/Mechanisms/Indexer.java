package frc.robot.Mechanisms;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Indexer {
    Ultrasonic m_bottomSensor;
    Ultrasonic m_secondSensor; //talk to designers about this. And John. Really depends on how this indexer actually works
    //actually. wait, its not necessary, if we intake a second ball as is we're penalized
    ColorSensorV3 m_topSensor;
    TalonSRX m_indexerMotor;

    public Indexer() {
        m_bottomSensor = new Ultrasonic(1, 2); //placeholders
        m_indexerMotor = new TalonSRX(14);

        if (ballInBottom()) {
            m_indexerMotor.set(ControlMode.PercentOutput, .9); //set to one if possible
        } else {
            m_indexerMotor.set(ControlMode.PercentOutput, 0);
        }

        SmartDashboard.putBoolean("Ball", ballInTop());


    }


    public boolean ballInBottom() {

        if (m_bottomSensor.getRangeMM() < 100) { //placeholder
            return true;
        } else {return false;}

    }


    public boolean ballInTop() {
        return false;
    }


    public String ColorOnDeck() {
        return null;
    }


    public boolean empty() {
        
        if (!ballInTop() && !ballInBottom()) {
            return true;
        }
        
        return false;    
    }
    



}
