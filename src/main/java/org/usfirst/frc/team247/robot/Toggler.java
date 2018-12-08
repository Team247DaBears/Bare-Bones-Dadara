package org.usfirst.frc.team247.robot;

public class Toggler {
	int myState = 2;

	boolean changeBool = false;
	boolean returnBool = false;
	
	boolean invertResult = false;
	
	Toggler(){}
	
	boolean main(){
		switch (this.myState) {
		case 0:// Idle
			if (changeBool) {
				myState = 1;
			}
			break;
		case 1:
			if (!changeBool) {
				myState = 2;
			}
			break;
		case 2:
			if (changeBool) {
				myState = 3;
			}
			break;
		case 3:
			if (!changeBool) {
				myState = 4;
			}
			break;
		case 4:
			if (changeBool) {
				myState = 1;
			}
			break;
		}// end of switch

		
		// now set the output
		switch (myState) {
		case 0:
		case 3:
		case 4:
			returnBool = true;
			break;
		case 1:
		case 2:
			returnBool = false;
			break;
		}
		
	if(invertResult) {return returnBool;} //This fixes the toggler's default being an undesirable state. Quick and dirty made 18/29/3
	return !returnBool;
	}
	
	void updateState(boolean update){
		changeBool = update;
	}
}
