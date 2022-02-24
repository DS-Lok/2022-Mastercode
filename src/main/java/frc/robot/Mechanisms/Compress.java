package frc.robot.Mechanisms;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;

public class Compress {
    public void run(Compressor COMPY){
        COMPY.enableAnalog(30,110);
    }
}
