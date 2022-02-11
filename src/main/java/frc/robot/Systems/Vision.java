package frc.robot.Systems;

import edu.wpi.first.networktables.NetworkTableInstance;

//this will hold the the commands for both the limelight and ball processing
public class Vision {

    public static double DistanceFromTarget() {
        if (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1) {return 0;}
        else {

            return 0;
        }

    }


    public static double AngleFromTarget() {
        return 0;
    }
    
   

}
