package frc.robot.Mechanisms;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class Collector {
    
    private final DoubleSolenoid m_LeftPiston;
    private final DoubleSolenoid m_RightPiston;


    private final CANSparkMax m_collectorNeo;

    private RelativeEncoder m_encoder;
    private SparkMaxPIDController m_pidController;
    //private final Drivetrain m_drive = new Robot().m_Drive;

    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

    double setPoint;
    double processVariable;



    public Collector() {
        m_LeftPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
        m_RightPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);


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

    public void dropped(boolean pistonsOut) { //true is pistons out, false is not
        if (pistonsOut = true) {
            m_LeftPiston.set(Value.kForward);
            m_RightPiston.set(Value.kForward);

            //possibly setting kOff will depresurize them and give them compressability?
            m_LeftPiston.set(Value.kOff); 
            m_RightPiston.set(Value.kOff);

        } else if (pistonsOut = false) {
            m_LeftPiston.set(Value.kReverse);
            m_RightPiston.set(Value.kReverse);
        }
    }


    public void run(Boolean On) {
        double defaultSpeed = 1807; //placeholderValue
        double Speed;
        //if (defaultSpeed < neededSpeed(m_drive.Speed())){
          //  Speed = neededSpeed(m_drive.Speed());
      //  } else {Speed = defaultSpeed;}

        if (On = true) {
     //   m_pidController.setReference(Speed, CANSparkMax.ControlType.kVelocity); 
        }
    }

    public double neededSpeed(double DriveTrainSpeed) {
      
      
        return 0;
    }


}
