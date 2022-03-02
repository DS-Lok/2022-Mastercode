package frc.robot.Systems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

//this will hold the the commands for both the limelight and ball processing
public class Vision {

    static NetworkTable LLtable = NetworkTableInstance.getDefault().getTable("limelight");
    static double limLightAngle = 35.0; //PLACEHOLDER
    static double limLightHeight = 36; //PLACEHOLDER
    static double targetHeight = 72; //PLACEHOLDER
    
    public Vision(){
        LLtable.getEntry("ledMode").setNumber(3);
    }

    //all in inches


    public void enableLimelight(Boolean on){
        if(on) LLtable.getEntry("ledMode").setNumber(3);
        else LLtable.getEntry("ledMode").setNumber(1);
    }

    

    public static double DistanceFromTarget() {
        double vertDis = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);
        if (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1) {     
            double targetAngle = vertDis + limLightAngle;
            double targetAngleRadians = targetAngle * (3.14159 / 180.0);
            double tarDis = (targetHeight - limLightHeight)/Math.tan(targetAngleRadians);
            return tarDis;
        }
        else {
            return 0;
        }
    }

    public static double AngleFromTarget() {
        double horDis = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
        return horDis;
    }

    public static double AngleFromBall() {
        double horDis = NetworkTableInstance.getDefault().getTable("SmartDashboard").getEntry("TargetX").getDouble(0.0);
        return horDis;
    }
    
   




}
