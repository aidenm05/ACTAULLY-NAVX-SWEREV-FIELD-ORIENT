package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
public class Robot extends TimedRobot {

  Joystick joystick = new Joystick(1);

  TalonFX fldrive = new TalonFX(4);
  TalonFX frdrive = new TalonFX(6);
  TalonFX bldrive = new TalonFX(1);
  TalonFX brdrive = new TalonFX(2);

  TalonFX flsteer = new TalonFX(0);
  TalonFX frsteer = new TalonFX(5);
  TalonFX blsteer = new TalonFX(7);
  TalonFX brsteer = new TalonFX(3);

  CANCoder flcancoder = new CANCoder(11); 
  CANCoder frcancoder = new CANCoder(8); 
  CANCoder blcancoder = new CANCoder(9); 
  CANCoder brcancoder = new CANCoder(10); 
 
  AHRS navx = new AHRS(SPI.Port.kMXP);

CANCoderConfiguration config = new CANCoderConfiguration();


@Override
public void robotInit() {
    navx = new AHRS(SPI.Port.kMXP);
}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {}


  @Override
  public void teleopPeriodic() {
      // Read the encoder values from the CANCoders
      double flEncoderValue = flcancoder.getPosition();
      double frEncoderValue = frcancoder.getPosition();
      double blEncoderValue = blcancoder.getPosition();
      double brEncoderValue = brcancoder.getPosition();
    
      // Calculate the average encoder value
      double averageEncoderValue = (flEncoderValue + frEncoderValue + blEncoderValue + brEncoderValue) / 4;
    
      // Set the steer motor outputs to align the wheels
      flsteer.set(ControlMode.Position, averageEncoderValue + joystick.getRawAxis(0));
      frsteer.set(ControlMode.Position, averageEncoderValue + joystick.getRawAxis(0));
      blsteer.set(ControlMode.Position, averageEncoderValue + joystick.getRawAxis(0));
      brsteer.set(ControlMode.Position, averageEncoderValue + joystick.getRawAxis(0));
    
      // Set the drive motor outputs based on the joystick input
      fldrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
      frdrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
      bldrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
      brdrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
  
        
  // fldrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
  // frdrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
  // bldrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
  // brdrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));

  // flsteer.set(ControlMode.PercentOutput, joystick.getRawAxis(4));
  // frsteer.set(ControlMode.PercentOutput, joystick.getRawAxis(4));
  // blsteer.set(ControlMode.PercentOutput, joystick.getRawAxis(4));
  // brsteer.set(ControlMode.PercentOutput, joystick.getRawAxis(4));

    // double forward = joystick.getRawAxis(1);
    // double strafe = joystick.getRawAxis(0);
    double rotation = joystick.getRawAxis(4);

      
      
      
      
      // added joystick axis for angle
  
      // convert angle from joystick to radians
      // angle = Math.toRadians(angle);
      
      // calculate the x and y component of the joystick input
      // double x = forward * Math.cos(angle) - strafe * Math.sin(angle);
      // double y = forward * Math.sin(angle) + strafe * Math.cos(angle);
  
      // use the x and y component to set the drive motor output
      // fldrive.set(ControlMode.PercentOutput, x);
      // frdrive.set(ControlMode.PercentOutput, y);
      // bldrive.set(ControlMode.PercentOutput, x);
      // brdrive.set(ControlMode.PercentOutput, y);
  
      // use the rotation input to set the steer motor output
      flsteer.set(ControlMode.PercentOutput, rotation);
      frsteer.set(ControlMode.PercentOutput, rotation);
      blsteer.set(ControlMode.PercentOutput, rotation);
      brsteer.set(ControlMode.PercentOutput, rotation);
  
      // get the encoder position for each wheel
      double flPosition = flcancoder.getPosition();
      double frPosition = frcancoder.getPosition();
      double blPosition = blcancoder.getPosition();
      double brPosition = brcancoder.getPosition();

      flsteer.setSelectedSensorPosition(flPosition);
      frsteer.setSelectedSensorPosition(frPosition);
      blsteer.setSelectedSensorPosition(blPosition);
      brsteer.setSelectedSensorPosition(brPosition);

      // print the encoder position for each wheel
      System.out.print("FL Position: " + flPosition);
      System.out.print("FR Position: " + frPosition);
      System.out.print("BL Position: " + blPosition);
      System.out.print("BR Position: " + brPosition);
  
      // Use the NavX to get the yaw angle of the robot
      // double yaw = navx.getYaw();
  
      // Use the encoders and yaw angle to keep the wheels pointed in the same direction
      // flsteer.set(ControlMode.Position, flPosition);
      // frsteer.set(ControlMode.Position, frPosition);
      // blsteer.set(ControlMode.Position, blPosition);
      // brsteer.set(ControlMode.Position, brPosition);
  

}


  

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
