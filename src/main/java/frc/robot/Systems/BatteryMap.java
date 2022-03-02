package frc.robot.Systems;

import edu.wpi.first.wpilibj.DriverStation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class BatteryMap {
    int matchNumber = DriverStation.getMatchNumber();
    FileWriter map;
    public BatteryMap(){
        File matchRecord = new File("FilePath/HatboroMatch" + matchNumber);
    }
    
    public void driveTrainMotors(WPI_TalonFX fr,WPI_TalonFX mr,WPI_TalonFX br,WPI_TalonFX fl,WPI_TalonFX ml,WPI_TalonFX bl){
        
    }

}
