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
    static int climbState = 0;
    boolean hooked = false;
    DoubleSolenoid bigClimbPiston = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 0);
    WPI_TalonFX winch1 = new WPI_TalonFX(9);
    WPI_TalonFX winch2 = new WPI_TalonFX(10);
    int UpperLimit = 18000;
    int LowerLimit = -180000;
    

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

    public static void initClimb(boolean setFalse){
       
    }

    public void runWinch(Double joyOut){
        winch1.set(ControlMode.PercentOutput, joyOut *.5);
    }
    public void activatePiston(boolean TOGGLE, DoubleSolenoid SOLENOID){
        if(TOGGLE){
            SOLENOID.toggle();
        }
        BatteryMap.climbValues(winch1, winch2, SOLENOID);  
    }


    public void nextRung(boolean on, DoubleSolenoid climbSolenoid){
        SmartDashboard.putNumber("Climb State", climbState);
        if(on)climbState = 1;
        int x = 0;
        if(winch1.getSelectedSensorPosition() < UpperLimit || 
        winch2.getSelectedSensorPosition() < UpperLimit && climbState == 1){

            winch1.set(ControlMode.PercentOutput, .3);
        }



        else if(winch1.getSelectedSensorPosition() > UpperLimit || winch2.getSelectedSensorPosition() > UpperLimit && climbState == 1) climbState = 2;
        
        
        
        if(climbState == 2){

        winch1.set(ControlMode.PercentOutput, 0);
        climbSolenoid.set(Value.kForward);
        climbState = 3;
    }



        if(climbState ==3 && x != 20){
            x++;
        }



        else if(climbState ==3 & x == 20)climbState =4;


        if(climbState ==4 && winch1.getSelectedSensorPosition() > LowerLimit 
        || winch2.getSelectedSensorPosition() > LowerLimit){

            winch1.set(ControlMode.PercentOutput, -.3);
            climbSolenoid.set(Value.kReverse);
        }
        
        
        else if(climbState == 4 && winch1.getSelectedSensorPosition() < LowerLimit 
        || winch2.getSelectedSensorPosition() < LowerLimit){

            winch1.set(ControlMode.PercentOutput, 0);
            climbState = 0;
        }
    
    
    }
}


