package org.usfirst.frc.team247.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SPI;


public class Io {       


    public static final int  JOYSTICKPS2PORT=8;
    public static final int TANKDRIVEONLEFT=1;
    public static final int TANKDRIVEONRIGHT=2;

    Joystick drivePs2 = new Joystick(JOYSTICKPS2PORT);
    Joystick tankLeft = new Joystick(TANKDRIVEONLEFT);
    Joystick tankRight = new Joystick(TANKDRIVEONRIGHT);
    Joystick manipPs2 = new Joystick(0);

    public Talon leftDrive1 = new Talon(0);
    public Talon leftDrive2 = new Talon(1);
    public Talon rightDrive1 = new Talon(2);
    public Talon rightDrive2 = new Talon(3);
    
    Talon armMotor = new Talon(6);
    Talon wristMotor = new Talon(7);
    
    Relay leftRoller = new Relay(0);
	Relay rightRoller = new Relay(1);
    
    Solenoid shifter = new Solenoid(0);
    Solenoid wristOpen = new Solenoid(5);
    Solenoid levelLED = new Solenoid(7);

     
    DoubleSolenoid leftHook = new DoubleSolenoid(2,3);
   // DoubleSolenoid rightHook = new DoubleSolenoid(4,5);
    

	
	

    UsbCameraHandler cam = new UsbCameraHandler();
    
    
    Io() {
    
    	leftRoller.setDirection(Direction.kBoth);
    	rightRoller.setDirection(Direction.kBoth);
    	wristMotor.setInverted(false);
    	armMotor.setInverted(false);
    	rightDrive1.setInverted(true);
    	rightDrive2.setInverted(true);
 

    }
     
     
     public boolean isABot()
     {
    	 return true;
     }
     
    
    
	
}
