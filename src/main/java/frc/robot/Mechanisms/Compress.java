package frc.robot.Mechanisms;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Compress {
    public void run(Compressor COMPY){
        SmartDashboard.putNumber("Pressure Switch Value", COMPY.getPressure());
        COMPY.enableAnalog(60,120);

        //COMPY.enableDigital();

        //Pnuematics Ports
        //5/4 = climb
        //1/0 = collector
    }
}
