import java.util.Random;

public class ComboLock {
	private int code1, code2, code3;
	private int cam1, cam2;
	private boolean gate1, gate2, gate3;
	
	Random STARTING_POINT;
	
	public int currentTick; //The dial of the combination lock
	
	public ComboLock(int[] combo) {
		code1 = combo[0]; //Records the code supplied by the user
		code2 = combo[1];
		code3 = combo[2];
		
		STARTING_POINT = new Random();
		currentTick = STARTING_POINT.nextInt(39); //Less rigid user experience
		cam1 = STARTING_POINT.nextInt(37)+2;
		cam2 = STARTING_POINT.nextInt(37)+2;
		if(cam1 == cam2) cam1++; //These checks ensure that the dial and subsequent cams are not aligned on the same tick
		if(cam1 == 40) cam1 = 0;
		if(cam1 == currentTick) currentTick++;
		if(currentTick == 40) currentTick = 0;
	}
	
	public void turnRight(int ticks) { //Turn the dial to the right, updates cams accordingly
		for(; ticks > 0; ticks--) {
			currentTick++;
			if(currentTick > 39) currentTick = 0;
			if(currentTick == cam1) cam1++;
			if(cam1 > 39) cam1 = 0;
			if(cam1 == cam2) cam2++;
			if(cam2 > 39) cam2 = 0;
			
			checkSet();
		}
	}
	
	public void turnLeft(int ticks) { //Turn the dial to the left, updates cams accordingly
		for(; ticks > 0; ticks--) {
			currentTick--;
			if(currentTick < 0) currentTick = 39;
			if(currentTick == cam1) cam1--;
			if(cam1 < 0) cam1 = 39;
			if(cam1 == cam2) cam2--;
			if(cam2 < 0) cam2 = 39;
			
			checkSet();
		}
	}
	
	private void checkSet() { //This method is unnecessary, but helped with refining expected behavior.
		gate1 = (cam2 == (code1>37 ? code1-38 : code1+2)) ? true : false;
		gate2 = (cam1 == (code2<1 ? code2+39 : code2-1)) ? true : false;

		gate3 = (currentTick == code3) ? true : false;
	}
	
	public boolean open() { //Since combination locks, at least MasterLock variety, pop open, this function pops the lock once the gates are aligned
		if(gate1 && gate2 && gate3) return true;
		return false;
	}
	
	public void gateOpenOrClosed() { //Debug method
		System.out.println("Gate1 is " + (gate1 ? "open" : "closed") + " " + cam2);
		System.out.println("Gate2 is " + (gate2 ? "open" : "closed") + " " + cam1);
		System.out.println("Gate3 is " + (gate3 ? "open" : "closed"));
	}
}
