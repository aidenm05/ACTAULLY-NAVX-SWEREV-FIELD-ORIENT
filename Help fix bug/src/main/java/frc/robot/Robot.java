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

  CANCoder flcancoder = new CANCoder(8); 
  CANCoder frcancoder = new CANCoder(9); 
  CANCoder blcancoder = new CANCoder(10); 
  CANCoder brcancoder = new CANCoder(11); 
 
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
  // 1st - 3 objectives
// Deposit preloaded cone to highest left
// go backward
// turn right
// pick up cube
// back - turn left - straight
// deposit cube to highest
// go back - turn right
// pick up cone/cube
// back - turn left - straight
// deposit cube to 2nd level/deposit cone to highest and score 1 trio thing

// 2nd - 2 objectives
// deposit preloaded cone to highest left
// back - right 
// pick up cube
// back - left - straight
// deposit highest
// back
// go to middle
// reverse onto charging station

// 3rd - 1 objective
// deposit preloaded cone to highest left
// back - right
// pick up cube
// back - left - straight
// deposit highest
// back
// go to middle
// reverse onto charging station
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {}


@Override
public void teleopPeriodic() {

  fldrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
  frdrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
  bldrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
  brdrive.set(ControlMode.PercentOutput, joystick.getRawAxis(1));

  flsteer.set(ControlMode.PercentOutput, joystick.getRawAxis(4));
  frsteer.set(ControlMode.PercentOutput, joystick.getRawAxis(4));
  blsteer.set(ControlMode.PercentOutput, joystick.getRawAxis(4));
  brsteer.set(ControlMode.PercentOutput, joystick.getRawAxis(4));

  double forward = joystick.getRawAxis(1);
  double strafe = joystick.getRawAxis(0);
  double rotation = joystick.getRawAxis(4);

  double flPosition = flcancoder.getPosition();
  double frPosition = frcancoder.getPosition();
  double blPosition = blcancoder.getPosition();
  double brPosition = brcancoder.getPosition();

  System.out.println("FL Position: " + flPosition);
  System.out.println("FR Position: " + frPosition);
  System.out.println("BL Position: " + blPosition);
  System.out.println("BR Position: " + brPosition);

  // Use the NavX to get the yaw angle of the robot
  double yaw = navx.getYaw();
  
  // Use the encoders and yaw angle to keep the wheels pointed in the same direction
  flsteer.set(ControlMode.Position, flPosition);
  frsteer.set(ControlMode.Position, frPosition);
  blsteer.set(ControlMode.Position, blPosition);
  brsteer.set(ControlMode.Position, brPosition);

 // Use the d-pad inputs to control the drive motors in a field-oriented manner
if(joystick.getPOV() == 0) { // forward
  fldrive.set(ControlMode.PercentOutput, forward * Math.cos(yaw) - strafe * Math.sin(yaw));
  frdrive.set(ControlMode.PercentOutput, forward * Math.sin(yaw) + strafe * Math.cos(yaw));
  bldrive.set(ControlMode.PercentOutput, forward * Math.cos(yaw) + strafe * Math.sin(yaw));
  brdrive.set(ControlMode.PercentOutput, forward * Math.sin(yaw) - strafe * Math.cos(yaw));
} else if(joystick.getPOV() == 90) { // right
  fldrive.set(ControlMode.PercentOutput, strafe * Math.cos(yaw) + forward * Math.sin(yaw));
  frdrive.set(ControlMode.PercentOutput, strafe * Math.sin(yaw) - forward * Math.cos(yaw));
  bldrive.set(ControlMode.PercentOutput, strafe * Math.cos(yaw) - forward * Math.sin(yaw));
  brdrive.set(ControlMode.PercentOutput, strafe * Math.sin(yaw) + forward * Math.cos(yaw));
} else if(joystick.getPOV() == 180) { // backward
  fldrive.set(ControlMode.PercentOutput, -forward * Math.cos(yaw) - strafe * Math.sin(yaw));
  frdrive.set(ControlMode.PercentOutput, -forward * Math.sin(yaw) + strafe * Math.cos(yaw));
  bldrive.set(ControlMode.PercentOutput, -forward * Math.cos(yaw) + strafe * Math.sin(yaw));
  brdrive.set(ControlMode.PercentOutput, -forward * Math.sin(yaw) - strafe * Math.cos(yaw));
} else if(joystick.getPOV() == 270) { // left
  fldrive.set(ControlMode.PercentOutput, -strafe * Math.cos(yaw) + forward * Math.sin(yaw));
  frdrive.set(ControlMode.PercentOutput, -strafe * Math.sin(yaw) - forward * Math.cos(yaw));
  bldrive.set(ControlMode.PercentOutput, -strafe * Math.cos(yaw) - forward * Math.sin(yaw));
  brdrive.set(ControlMode.PercentOutput, -strafe * Math.sin(yaw) + forward * Math.cos(yaw));
} else {
fldrive.set(ControlMode.PercentOutput, 0);
frdrive.set(ControlMode.PercentOutput, 0);
bldrive.set(ControlMode.PercentOutput, 0);
brdrive.set(ControlMode.PercentOutput, 0);
}


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