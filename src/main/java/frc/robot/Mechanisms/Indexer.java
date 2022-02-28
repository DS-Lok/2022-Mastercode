package frc.robot.Mechanisms;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class Indexer {

    Timer timeout = new Timer();
    
    Color detectedColor;
    //Color Sensor 
    private I2C.Port i2cPort = I2C.Port.kOnboard;
    private ColorSensorV3 m_colorS = new ColorSensorV3(i2cPort);
    private ColorMatch m_colorMatch = new ColorMatch();
    //Color targeting
    private Color BLUE = new Color(0, 0, 0.5);
    private Color RED = new Color(0.6, 0, 0);
    private Color NONE = new Color (0,0,0);

    //UltraSonics 
    private Ultrasonic iUltrasonic1 = new Ultrasonic(1, 0);
    private Ultrasonic iUltrasonic2 = new Ultrasonic(3, 2);
    
    //Motor
    WPI_TalonSRX indexerWheel; 

    boolean HARDSTOP;
    boolean IndexStatus = false;

    boolean TOPDETECTED;
    boolean BOTDETECTED;


//Indexer Object 
public Indexer(){
    indexerWheel = new WPI_TalonSRX(13);
    
}





//Color Sensor Method 
public String  ColorSensor(){
    m_colorMatch.addColorMatch(RED);
    m_colorMatch.addColorMatch(BLUE);
    m_colorMatch.addColorMatch(NONE);
//Detecting and result 
detectedColor = m_colorS.getColor();
String colorString;
ColorMatchResult match = m_colorMatch.matchClosestColor(detectedColor);

//Matching Colors 
if(match.color.equals(BLUE)){
    colorString = "Blue";
}else if(match.color.equals(RED)){
    colorString = "Red";
}else{
    colorString = "Unimportant";
}

//Printing all values to the SmartDash
SmartDashboard.putNumber("Red",detectedColor.red);
SmartDashboard.putNumber("Blue", detectedColor.blue);
SmartDashboard.putString("Color", colorString);
return colorString;
}


public void setIndex(){
    IndexStatus = false;
}


//UltraSonic Method 
public void i_UltraSonic(){
iUltrasonic1.setAutomaticMode(true);
iUltrasonic2.setAutomaticMode(true);
SmartDashboard.putNumber("BOTTOM SENSOR", iUltrasonic1.getRangeInches());
SmartDashboard.putBoolean("BOTTOM SENSOR DETECTING", BOTDETECTED);
SmartDashboard.putNumber("TOP SENSOR", iUltrasonic2.getRangeInches());
SmartDashboard.putBoolean("TOP SENSOR DETECTING", TOPDETECTED);
SmartDashboard.putBoolean("IndexStatus", IndexStatus);

//Ultrasonic States accounting for errors in the ultrasonic itself
if(iUltrasonic2.getRangeInches() < 7 || iUltrasonic2.getRangeInches() > 20) TOPDETECTED = true;
else TOPDETECTED = false;

if(iUltrasonic1.getRangeInches() < 7 || iUltrasonic1.getRangeInches() > 20) BOTDETECTED = true;
else BOTDETECTED = false;

//If ball is detected in bottom but not top, cycle bottom ball to top 
if(BOTDETECTED && !TOPDETECTED){
    IndexStatus = true;
}    
//When ball reaches top, cut indexer
else if(TOPDETECTED){
    IndexStatus = false;
}

//If a ball detected in both spots, cut the indexer
if(BOTDETECTED && TOPDETECTED) HARDSTOP = true;
else HARDSTOP = false;

SmartDashboard.putBoolean("HARDSTOP", HARDSTOP);
}




public void COLLECT(Boolean RUN, Boolean OTHER){
    if((RUN || IndexStatus)  && !HARDSTOP){
        indexerWheel.set(-0.2);
    }
    else if(OTHER){
        indexerWheel.set(0.6);
    }
    else{
        indexerWheel.set(0);
    }
}






    





public void index(){

        i_UltraSonic();
        
}

}

  
 

 


 





    




