package frc.robot.Mechanisms;

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
    WPI_TalonFX winch = new WPI_TalonFX(0);

    public Climb(XboxController m_operateContoller){
        if(m_operateContoller.getPOV()==0){
            climbState = true;
            //insert code to enable climb

            if(climbEnabled() && m_operateContoller.getAButton()){
                bigClimbPiston.set(Value.kForward);
                if(winch.getSelectedSensorPosition() < 200){
                    winch.set(0.3);
                    
                }
                else if(winch.getSelectedSensorPosition() >= 200){
                    winch.set(-.3);
                    hooked = true;
                }
            }
        }
    }
    public boolean climbEnabled(){
        return climbState;
    }
    public static void initClimb(boolean setFalse){
        climbState = false;
    }
}
