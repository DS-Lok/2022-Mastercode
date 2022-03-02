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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Systems.BatteryMap;

public class Climb {
    static boolean climbState;
    boolean hooked = false;
    DoubleSolenoid bigClimbPiston = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 0);
    WPI_TalonFX winch1 = new WPI_TalonFX(9);
    WPI_TalonFX winch2 = new WPI_TalonFX(10);
    int UpperLimit = 18000;
    int LowerLimit = -18000;
    

    public Climb(){
        winch2.setInverted(true);
        winch2.follow(winch1);

        winch1.setNeutralMode(NeutralMode.Brake);
        winch2.setNeutralMode(NeutralMode.Brake);
        

    }



    public void readEncoder() {
        SmartDashboard.putNumber("Winch 1 encoder", winch1.getSelectedSensorPosition());
        SmartDashboard.putNumber("Winch 2 encoder", winch2.getSelectedSensorPosition());  
            
    }

    public boolean climbEnabled(){
        return climbState;
    }
    public static void initClimb(boolean setFalse){
        climbState = false;
    }

    public void runWinch(Double joyOut){
        if(winch1.getSelectedSensorPosition() != UpperLimit && winch1.getSelectedSensorPosition() != LowerLimit) winch1.set(ControlMode.PercentOutput, joyOut *.3);
        else winch1.set(ControlMode.PercentOutput, 0);
        
    }
    public void activatePiston(Boolean TOGGLE, DoubleSolenoid SOLENOID){
        if(TOGGLE){
            SOLENOID.toggle();
        }
        BatteryMap.climbValues(winch1, winch2, SOLENOID);  
    }
}
