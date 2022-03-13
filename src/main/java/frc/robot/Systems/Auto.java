package frc.robot.Systems;


//File not currently used

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Auto {

    public static void recordSequence(String fileName, double Input) {
        FileWriter log;
            try {
                log = new FileWriter("LocationLogger\\"+fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        
    }


    public static double readSequence(String fileName) {
        return 0;
    }
}
