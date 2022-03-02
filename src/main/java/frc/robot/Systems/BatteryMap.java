package frc.robot.Systems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class BatteryMap {
    int matchNumber = DriverStation.getMatchNumber();
    FileWriter map;
    double vfr,vmr,vbr,vfl,vml,vbl,vw1,vw2,vcollect,vs1,vs2;
    String coPiston, clPiston;
    double vcomp;
    public BatteryMap(){
        File matchRecord = new File("FilePath/HatboroMatch" + matchNumber);
    }
    

    public void driveTrainMotors(WPI_TalonFX fr,WPI_TalonFX br,WPI_TalonFX fl,WPI_TalonFX bl){
        vfr = fr.getMotorOutputVoltage();
        //vmr = mr.getMotorOutputVoltage();
        vbr = fr.getMotorOutputVoltage();
        vfl = fl.getMotorOutputVoltage();
        //vml = ml.getMotorOutputVoltage();
        vbl = bl.getMotorOutputVoltage();

        

    }

    public void climbValues(WPI_TalonFX w1,WPI_TalonFX w2, DoubleSolenoid piston){
        vw1 = w1.getMotorOutputVoltage();
        vw2 = w2.getMotorOutputVoltage();
        clPiston = piston.get().name();
    }
    public void shooterMotors(WPI_TalonFX s1,WPI_TalonFX s2){
        vs1 = s1.getMotorOutputVoltage();
        vs2 = s2.getMotorOutputVoltage();
    }
    public void collectValues(WPI_TalonFX collect, DoubleSolenoid piston){
        vcollect = collect.getMotorOutputVoltage();
        coPiston = piston.get().name();
    }
    public void comp(Compressor comp){
        vcomp = comp.getCurrent();
    }

    public void postValues(){
        
    }
}
