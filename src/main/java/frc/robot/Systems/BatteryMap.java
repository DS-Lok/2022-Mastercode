package frc.robot.Systems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BatteryMap {

//Volts: getMotorOutputVoltage
//Amps:  getSupplyCurrent


    int matchNumber = DriverStation.getMatchNumber();
    FileWriter map;
    static double vfr,vmr,vbr,vfl,vml,vbl,vw1,vw2,vcollect,vs1,vs2,vcomp, vindex = 0;
    static double tfr,tmr,tbr,tfl,tml,tbl,tw1,tw2,tcollect,ts1,ts2,tcomp,tindex = 0;
    static String coPiston, clPiston;
    static int shootTimer, collectTimer, driveTimer = 0;
    
    public BatteryMap(){
        //File matchRecord = new File("FilePath/HatboroMatch" + matchNumber);
    }
    

    static public void driveTrainMotors(WPI_TalonFX fr,WPI_TalonFX br,WPI_TalonFX fl,WPI_TalonFX bl,WPI_TalonFX mr,WPI_TalonFX ml, boolean driving){
        vfr = fr.getSupplyCurrent();
        vmr = mr.getSupplyCurrent();
        vbr = fr.getSupplyCurrent();
        vfl = fl.getSupplyCurrent();
        vml = ml.getSupplyCurrent();
        vbl = bl.getSupplyCurrent();
 
        tfr = tfr + vfr;
        tbr = tbr + vbr;
        tfl = tfl + vfl;
        tbl = tbl + vbl;
        tmr = tmr+vmr;
        tml = vml +tml;
        if(driving) driveTimer++;
        
    }

     static public void climbValues(WPI_TalonFX w1,WPI_TalonFX w2, DoubleSolenoid piston){
        vw1 = w1.getSupplyCurrent();
        vw2 = w2.getSupplyCurrent();
        clPiston = piston.get().name();

        tw1 = tw1+vw1;
        tw2 = tw2+vw2;

    }
    static public void shooterMotors(WPI_TalonFX s1,WPI_TalonFX s2, boolean on){
        vs1 = s1.getSupplyCurrent();
        vs2 = s2.getSupplyCurrent();

        ts1 = vs1+ts1;
        ts2 = vs2 + ts2;

        if(on) shootTimer++;
    }
    public static void collectValues(WPI_TalonFX collect, boolean on){
        vcollect = collect.getSupplyCurrent();
        tcollect = tcollect + vcollect;
        if(on) collectTimer++;
    }
    public static  void comp(Compressor comp){
        vcomp = comp.getCurrent();

        tcomp = tcomp + vcomp;

    }

    public static void indexerValues(WPI_TalonSRX index){
        vindex = index.getSupplyCurrent();
        tindex = tindex+vindex;
    }

    public static void postInstantaneousAmps(){
        SmartDashboard.putNumber("Front Right Amps", vfr);
        SmartDashboard.putNumber("Back Right Amps", vbr);
        SmartDashboard.putNumber("Front Left Amps", vfl);
        SmartDashboard.putNumber("Back Left Amps", vbl);
        SmartDashboard.putNumber("Middle Left Amps", vml);
        SmartDashboard.putNumber("Middle Right Amps", vmr);
        SmartDashboard.putNumber("Shooter 1 Amps", vs1);
        SmartDashboard.putNumber("Shooter 2 Amps", vs2);
        SmartDashboard.putNumber("Collector Amps", vcollect);
        SmartDashboard.putNumber("Compressor Amps", vcomp);
        SmartDashboard.putNumber("Indexer Amps", vindex);
        SmartDashboard.putNumber("Drive Timer", driveTimer);
    }
    public static void postAverageAmps(){
        SmartDashboard.putNumber("Front Right Amps in Iterations", tfr/driveTimer);
        SmartDashboard.putNumber("Back Right Amps in Iterations", tbr/driveTimer);
        SmartDashboard.putNumber("Front Left Amps in Iterations", tfl/driveTimer);
        SmartDashboard.putNumber("Back Left Amps in Iterations", tbl/driveTimer);
        SmartDashboard.putNumber("Shooter 1 Amps in Iterations", ts1/shootTimer);
        SmartDashboard.putNumber("Shooter 2 Amps in Iterations", ts2/shootTimer);
        SmartDashboard.putNumber("Collector Amps in Iterations", tcollect/collectTimer);
        SmartDashboard.putNumber("Middle Left Amps in Iterations", tml/driveTimer);
        SmartDashboard.putNumber("Middle Right Amps in Iterations", tmr/driveTimer);
    }
    public static void postTotalAmps(){
        SmartDashboard.putNumber("Front Right Amps", tfr);
        SmartDashboard.putNumber("Back Right Amps", tbr);
        SmartDashboard.putNumber("Front Left Amps", tfl);
        SmartDashboard.putNumber("Back Left Amps", tbl);
        SmartDashboard.putNumber("Shooter 1 Amps", ts1);
        SmartDashboard.putNumber("Shooter 2 Amps", ts2);
        SmartDashboard.putNumber("Collector Amps", tcollect);
        SmartDashboard.putNumber("Compressor Amps", tcomp);
        SmartDashboard.putNumber("Indexer Amps", tindex);
        SmartDashboard.putNumber("Middle Left Amps", tml);
        SmartDashboard.putNumber("Middle Right Amps", tmr);
    }

}
