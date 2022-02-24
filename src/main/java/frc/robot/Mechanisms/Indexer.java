package frc.robot.Mechanisms;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class Indexer {
//Color Sensor 
private I2C.Port i2cPort = I2C.Port.kOnboard;
private ColorSensorV3 m_colorS = new ColorSensorV3(i2cPort);
private ColorMatch m_colorMatch = new ColorMatch();
//Color targeting
private Color kBluetarget = new Color(0, 0, 0.75);
private Color kRedtarget = new Color(0.5, 0, 0);

//UltraSonics 
private Ultrasonic iUltrasonic1 = new Ultrasonic(1, 2);
private Ultrasonic iUltrasonic2 = new Ultrasonic(3, 4);
boolean IndexStatus = false;
//Motor
private WPI_TalonFX indexerWheel; 
//Indexer Object 
public Indexer(){}

//Color Sensor Method 
public void  ColorSensor(){
//Detecting and result 
Color detectedColor = m_colorS.getColor();
String colorString;
ColorMatchResult match = m_colorMatch.matchClosestColor(detectedColor);

//Matching Colors 
if(match.color == kBluetarget){
    colorString = "Blue";
}else if(match.color == kRedtarget){
    colorString = "Red";
}else{
    colorString = "Unimportant";
}

//Printing all values to the SmartDash
SmartDashboard.putNumber("Red",detectedColor.red);
SmartDashboard.putNumber("Blue", detectedColor.blue);
SmartDashboard.putString("Color", colorString);


}

//UltraSonic Method 
public void i_UltraSonic(){
iUltrasonic1.setAutomaticMode(true);
iUltrasonic2.setAutomaticMode(true);

//Status while collecting 



//Setting Status after ollected 
if(iUltrasonic1.getRangeInches() > 2 && !(iUltrasonic2.getRangeInches() > 2)){
    IndexStatus = true;
}else if(iUltrasonic2.getRangeInches() > 2){
    IndexStatus = false;
}

if(IndexStatus = true){
    indexerWheel.set(0.4);
}else{
    indexerWheel.set(0);
}

}



public void index(Boolean go){
    
    if(go){
        i_UltraSonic();
        ColorSensor();
    }


}

 }

  
 

 


 





    




