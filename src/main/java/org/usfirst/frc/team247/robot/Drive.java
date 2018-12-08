package org.usfirst.frc.team247.robot;



import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
	Talon leftDrive1;
	Talon leftDrive2;
	Talon rightDrive1;
	Talon rightDrive2;
	
	Joystick leftTank;
	Joystick rightTank;
	Joystick driveXBox;
	
	Solenoid shifter;
	
	Encoder leftEncoder;
	Encoder rightEncoder;
	
	static double DEADBAND = .25;
	
	
	//Additions 0312
	
	boolean rotationPIDEnabled=false;

	double KPRotate=1/30.0;
	double KDRotate=0.06;
	double KIRotate=0;
	

	
	Drive(Io io){
		leftDrive1 = io.leftDrive1;
		leftDrive2 = io.leftDrive2;
		rightDrive1 = io.rightDrive1;
		rightDrive2 = io.rightDrive2;
		
		leftTank = io.tankLeft;
		rightTank = io.tankRight;
		driveXBox = io.drivePs2;
		
		shifter = io.shifter;
		
	}
	
	void runtime() {
		tankDrive();
		shift();
		reportSensVals();
	}
	
	private void tankDrive() {
		double leftPower = -1*leftTank.getRawAxis(1);
		double rightPower = -1*rightTank.getRawAxis(1);
		leftDrive1.set(getDeadband(leftPower, DEADBAND));
		leftDrive2.set(getDeadband(leftPower, DEADBAND));
		rightDrive1.set(getDeadband(rightPower, DEADBAND));
		rightDrive2.set(getDeadband(rightPower, DEADBAND));
	}
	
	private void shift() {
		boolean downshift = leftTank.getRawButton(1);
		boolean upshift = rightTank.getRawButton(1);
		
		if (downshift) {shifter.set(false);}
		else if (upshift) {shifter.set(true);}
	}
	
	private double getDeadband(double input, double deadband) {
		if(Math.abs(input) < deadband) {return 0;}
		else {return input;}
	}
	
	private void reportSensVals() {
	//	SmartDashboard.putNumber("Left Encoder", leftEncoder.getRaw());
	//	SmartDashboard.putNumber("Right Encoder", rightEncoder.getRaw());
	//	if(leftTank.getRawButton(2)) {leftEncoder.reset(); rightEncoder.reset();}
	}
	

		

	
	
	
	private void setMotors(double left, double right )
	{
		System.out.println("Motors: "+left +" right "+right);
		if (rotationPIDEnabled)
		{

		rotationPIDEnabled=false;
		}
		
		leftDrive1.set(left);
		rightDrive1.set(right);
		leftDrive2.set(left);
		rightDrive2.set(right);
	}
	
	public void stop()
	{
		setMotors(0,0);
	}
	
	public void disable()
	{

		rotationPIDEnabled=false;
	}
}
