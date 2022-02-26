package frc.robot.Mechanisms;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;

public class Climb {
    static boolean climbState;
    boolean hooked = false;
    DoubleSolenoid bigClimbPiston = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 0);
    WPI_TalonFX winch1 = new WPI_TalonFX(9);
    WPI_TalonFX winch2 = new WPI_TalonFX(10);
    

    public Climb(){
        winch2.setInverted(true);
        winch2.follow(winch1);

        winch1.setNeutralMode(NeutralMode.Brake);
        winch2.setNeutralMode(NeutralMode.Brake);


    }
    public boolean climbEnabled(){
        return climbState;
    }
    public static void initClimb(boolean setFalse){
        climbState = false;
    }

    public void runWinch(Double joyOut){
       /* 
        if(FORWARDS)winch1.set(ControlMode.PercentOutput, .2);
        else if (BACKWARDS) winch1.set(ControlMode.PercentOutput, -.2);
        else winch1.set(ControlMode.PercentOutput, 0);
        */

        winch1.set(ControlMode.PercentOutput, joyOut *.3);
    }
    public void activatePiston(Boolean TOGGLE, DoubleSolenoid SOLENOID){
        if(TOGGLE){
            SOLENOID.toggle();
        }
    }
}
