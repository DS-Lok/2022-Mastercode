package frc.robot.Mechanisms;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;

public class Compress {
    Compressor compy = new Compressor(0, PneumaticsModuleType.REVPH);
    double CURRENT_PRESSURE;
    boolean COMPRESSING;
    public void run(){
        compy.enableAnalog(30,110);
    }
}
