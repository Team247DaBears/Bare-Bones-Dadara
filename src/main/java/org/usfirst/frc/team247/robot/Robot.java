/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team247.robot;





import edu.wpi.first.wpilibj.TimedRobot;


public class Robot extends TimedRobot {
	Io io;
	Manip manip;
	Drive drive;

	
	@Override
	public void robotInit() {
		io = new Io();

		drive = new Drive(io);
		manip = new Manip(io);


	}
	

	
	@Override
	public void autonomousInit() {
		
		
	}
	
	@Override
	public void autonomousPeriodic() {
		
	}

	
	

	
	public void teleopInit() {
		
	}
	
	@Override
	public void teleopPeriodic() {
		manip.runtime();
		drive.runtime();
	}
	
	@Override
	public void testInit()
	{
		
	}
	
	@Override
	public void testPeriodic() {
		manip.kickerPiston();
	//	manip.test();
	//	System.out.println("Arm: "+io.armSensor.get());
	//	System.out.println("Wrist: "+io.wristSensor.get());
	//	io.wristMotor.set(io.manipPs2.getRawAxis(5));
	}
	
	@Override
	public void disabledInit() {
		manip.prepForDisabled(); //Additions 0312
		drive.disable();
		System.out.println("Disabled Init Called");
	}
	
}
