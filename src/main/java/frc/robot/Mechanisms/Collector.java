package frc.robot.Mechanisms;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class Collector {
    
    private  DoubleSolenoid m_LeftPiston;
    
    //private DoubleSolenoid m_RightPiston = new DoubleSolenoid(PneumaticsModuleType.REVPH, 1, 0);


    private  CANSparkMax m_collectorNeo;

    private RelativeEncoder m_encoder;
    private SparkMaxPIDController m_pidController;
    //private final Drivetrain m_drive = new Robot().m_Drive;

    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

    double setPoint;
    double processVariable;



    public Collector() {

        m_LeftPiston = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
    

        m_collectorNeo = new CANSparkMax(11, MotorType.kBrushless);

        m_pidController = m_collectorNeo.getPIDController();
        m_encoder = m_collectorNeo.getEncoder();
        kP = 5e-5; 
        kI = 1e-6;
        kD = 0; 
        kIz = 0; 
        kFF = 0.000156; 
        kMaxOutput = 1; 
        kMinOutput = -1;


        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setIZone(kIz);
        m_pidController.setFF(kFF);
        m_pidController.setOutputRange(kMinOutput, kMaxOutput);

        m_collectorNeo.setIdleMode(IdleMode.kCoast);

    }

    public void dropped(boolean pistonsOut, boolean other) { //true is pistons out, false is not
        
        if (pistonsOut || other) {
            m_LeftPiston.set(Value.kForward);
           // m_RightPiston.set(Value.kForward);

            //possibly setting kOff will depresurize them and give them compressability?

        } else if (!pistonsOut || other) {
            m_LeftPiston.set(Value.kReverse);
            //m_RightPiston.set(Value.kReverse);
        }
    } 


    public void COLLECT(Boolean Dump, Boolean Collect) {
        if (Collect) {
            m_collectorNeo.set(.3);
        } else if (Dump) {
            
            m_collectorNeo.set(-.3);
        }
        else{
            m_collectorNeo.set(0);
        }
    }

  

    public double neededSpeed(double DriveTrainSpeed) {
      
      
        return 0;
    }


}
