import java.util.Scanner;

public class ComboLockTester {
	/* Since the idea is to replicate the action of a combination lock, I've decided to make this program work as if it were an actual combo lock.
	 * Technically this simplifies the user side of the program.
	 * I haven't used a combination lock in several years, so I laid out instructions reminding myself how they work.
	*/
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int input;
		boolean iDontKnowAGoodNameForThisValue = true;
		
		SillyGoose:
		while(iDontKnowAGoodNameForThisValue) {
			System.out.println("In front of you is a locker, and keeping you from it's contents, is a lock.");
			System.out.println("Since this is your locker, you should know the combination to the lock. What is it?");
			System.out.println("As an alternative option, you can break through the fabric of reality by inputting a negative number into your lock.");
			System.out.println();
			
			int[] combo = new int[3];
			
			
			for(int i = 0; i < 3; i++) {
				while(!in.hasNextInt()) {
					System.out.println("The dial only presents numbers.\nYou can write out each number individually, or seperate them with spaces.");
					System.out.println();
					in.next();
				}
				combo[i] = in.nextInt();
				if(combo[i] < 0) break SillyGoose;
				if(combo[i] >= 40) {
					System.out.println("Your combination lock has numbers from 0 to 39. You remember your combination, right?");
					continue SillyGoose;
				}
			}
			//System.out.println(combo[0] + " " + combo[1] + " " + combo[2]); //Debug line
			if(combo[2]+1 == combo[1] || combo[0]-39 == combo[1]) {
				System.out.println("\n***Due to design limitations in this brand of combination lock, that combination is not possible.***\n"); //An actual limitation of cheap combo locks, tolerances between cams doen't permit certain combinations
				continue SillyGoose; //I believe this is the only limitation in this program. Hope so, at least.
			}
			ComboLock locker = new ComboLock(combo);
			System.out.println("Now, dial in your combination.\nRemember!, its advised you turn the dial TWO full rotations to the right to ensure the interior mechanism is reset.\n");

			while(!locker.open()) {
				System.out.println("The dial is currently at " + locker.currentTick);
				System.out.println("1. Turn right\n2. Turn left\n");
				while(!in.hasNextInt()) {
					System.out.println("You only have two choices.");
				}
				input = in.nextInt();
				if(input < 0) break SillyGoose;
				
				switch(input) {
				case 1:
					System.out.println("How many ticks do you turn right?");
					while(!in.hasNextInt()) {
						System.out.println("Save the spells for Hogwarts.");
						in.next();
					} 
					input = in.nextInt();
					if(input < 0) break SillyGoose;
					locker.turnRight(input);
					break;
				case 2:
					System.out.println("How many ticks do you turn left?");
					while(!in.hasNextInt()) {
						System.out.println("You speak to the elder gods. They don't respond.");
						in.next();
					} 
					input = in.nextInt();
					if(input < 0) break SillyGoose;
					locker.turnLeft(input);
					break;
					default: System.out.println("That's not an option.");
				}
				//locker.gateOpenOrClosed(); //Debug line
			}
			System.out.println("\n\n***Click!***\nThe lock opens!");
			System.out.println("\nNow what?");
			System.out.println("1. Close the lock and open it again\n2. Walk away knowing you completed your task.");
			while(!in.hasNextInt()) {
				System.out.println("You ponder to yourself. What should you do?");
				in.next();
			}
			input = in.nextInt();
			switch(input) {
			case 1:
				System.out.println("You clamp the lock closed, its combination only known to you, and whoever is looking over your shoulder.");
				break;
			case 2:
				iDontKnowAGoodNameForThisValue = false;
				break;
			default: System.out.println("The weight of choosing between two options is an unforgiving burden."
										+ "\nThe potential of missed opportunity a consuming thought."
										+ "\nTo distract yourself, you fidget with the lock.");
			}
			
		}
		System.out.println("You walk away from your locker. Hopefully with everything you need.");
		in.close();
	}
}
