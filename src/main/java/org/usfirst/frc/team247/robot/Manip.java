package org.usfirst.frc.team247.robot;



import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.JoystickBase;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

@SuppressWarnings("deprecation")
public class Manip {
	Talon wrist;
	Talon elbow;
	
	Relay leftRoller;
	Relay rightRoller;
	
	Solenoid pincer;
	Solenoid levelLED;
	
	DoubleSolenoid leftLift;
	//DoubleSolenoid rightLift;
	//DoubleSolenoid.Value kForward;
	//DoubleSolenoid.Value kReverse;
	
	AnalogPotentiometer wristPot;
	AnalogPotentiometer elbowPot;
	
	Joystick manipStick;
	
	Toggler pincerToggle;
	Toggler rightLiftToggle;
	Toggler leftLiftToggle;
	

	
	
	static double DEADBAND = .1;
	
	static Value RELAY_OFF = Relay.Value.kOff;
	static Value RELAY_ON = Relay.Value.kOn;
	static Value RELAY_FORWARD = Relay.Value.kForward;
	static Value RELAY_REVERSE = Relay.Value.kReverse;
	
	//set arm params
	static double arm_length   = 44;
	static double wrist_length = 14;
	static double arm_hight    = 41;
	//set deductables to get usefull vals
	static double frame_deduct = 31;
	//static double wrist_deduct = 14;
	static double total_deduct = frame_deduct;
	//set arm limits
	static double frame_length      = 31;
	static double max_overreach     = 16;
	static double overreach_warning = 2;
	static double min_hight         = 0;
	static double max_elbow_angle   =  90;
	static double min_elbow_angle   = -40;
	static double max_wrist_angle   =  135;
	static double min_wrist_angle   = -125;
	
	@SuppressWarnings("static-access")
	Manip(Io io) {
		wrist = io.wristMotor;
		elbow = io.armMotor;
		leftRoller = io.leftRoller;
		rightRoller = io.rightRoller;
		
		pincer = io.wristOpen;
		levelLED = io.levelLED;
		

		
		manipStick = io.manipPs2;
		
		pincerToggle = new Toggler();

		leftLift = io.leftHook;
		if(leftLift==null) System.out.println("You passed in null");
		else System.out.println(leftLift.toString());
		//rightLift = io.rightHook;
		leftLiftToggle = new Toggler();
		rightLiftToggle = new Toggler();
		
	}
	
	
	public void runtime() {
		System.out.println("Manip.runtime");

		
			kickerPiston();
			manipulateWrist();
			manipulateArm();
			manipulateEndEffector();
			SmartDashboard.putString("Wrist Motion", "Manual");
			SmartDashboard.putString("Elbow Motion", "Manual");
		
		//reportSensorVals();

	}
	
	public void test() {
		manipulateEndEffector();
		manipulateWrist();
		manipulateArm();
	}



	public void prepForDisabled() {

		pincer.set(false);
		manipStick.setRumble(JoystickBase.RumbleType.kLeftRumble, 0);
		manipStick.setRumble(JoystickBase.RumbleType.kRightRumble, 0);
    }
	

	
	
	private void manipulateWrist() {
		double wristAxis = manipStick.getRawAxis(5) * -1;
		wrist.set((getDeadband(wristAxis, DEADBAND + .01)));
	}
   
	private void manipulateArm() {
		double armAxis = manipStick.getRawAxis(1) * -1;
		elbow.set((getDeadband(armAxis, DEADBAND)));
	}
   
	private void manipulateEndEffector() {
		int intakeVal = manipStick.getPOV();
		boolean pincerButton = manipStick.getRawButton(5);
		boolean pincerState = !pincerToggle.main();
		pincerToggle.updateState(pincerButton);
		
		switch(intakeVal) {
		case -1:
			leftRoller.set(RELAY_OFF);
			rightRoller.set(RELAY_OFF);
			break;
		
		case 0:
			leftRoller.set(RELAY_REVERSE);
			rightRoller.set(RELAY_FORWARD);
			break;
			
		case 90:
			leftRoller.set(RELAY_REVERSE);
			rightRoller.set(RELAY_REVERSE);
			break;
			
		case 180:
			leftRoller.set(RELAY_FORWARD);
			rightRoller.set(RELAY_REVERSE);
			break;
		
		case 270:
			leftRoller.set(RELAY_FORWARD);
			rightRoller.set(RELAY_FORWARD);
			break;
		}
		
		if(pincerState) {pincer.set(true);}
		else {pincer.set(false);}
	}
	
	private double getDeadband(double inputVal, double deadband) {
		if(Math.abs(inputVal) < deadband) {return 0;}
		else {return inputVal;}
	}
	
		
	
	
	
	public void kickerPiston() {
		if (leftLift==null) System.out.println("Itis null in kickerPiston");
		else System.out.println("So....its not null?");
		if(manipStick.getRawButton(1)) {
			System.out.println("");
			leftLift.set(DoubleSolenoid.Value.kForward);
			
			}
		if(manipStick.getRawButton(6)) {
			System.out.println("About to run");
			System.out.println(leftLift.get().toString());
			leftLift.set(DoubleSolenoid.Value.kReverse);	
		}
	}
}