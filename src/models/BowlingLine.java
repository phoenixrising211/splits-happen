package models;

/**
 * Model which represents a line of bowling. Has methods to score the game and
 * to translate bowl symbols into number of pins.
 * 
 * @author aaron.paloski
 *
 */
public class BowlingLine {

	private char[] bowls;
	
	public BowlingLine(String line){
		//TODO: validate input (out of scope)
		bowls = line.toCharArray();
	}
	
	/**
	 * Scores the line of bowling. See README for scoring logic.
	 * 
	 * @return Total score of the game
	 */
	public int score(){
		int totalScore = 0;
		
		/*
		 * Counting frames and bowls is out of scope, but it's the easiest way
		 * to account for bonus bowls at the end of the line.
		 */
		int frame = 1; 
		boolean secondBowl = false;
		
		int current = 0; //current index in bowls array
		while(frame <= 10){
			//first, add the number of pins knocked down on this bowl
			totalScore += pins(current);
			
			//next, handle special logic for spares and strikes
			char bowl = bowls[current];
			
			switch(bowl){
			case '/': //spare
				//TODO: check for missing bonus bowl (out of scope)
				//add the number of pins knocked down on the next bowl.
				totalScore += pins(current+1);
				break;
				
			case 'X': //strike
				//TODO: check for missing bonus bowls (out of scope)
				//add the number of pins knocked down on the next two bowls.
				totalScore += pins(current+1);
				totalScore += pins(current+2);
				break;
			}
			
			//finally, advance current position, frame number, and secondBowl
			current++;
			if(secondBowl || bowl == 'X'){
				 //if this was the second bowl or a strike, go to next frame
				secondBowl = false;
				frame++;
			}else{
				//otherwise, it's now the second bowl
				secondBowl = true;
			}
		}
		
		//after 10 frames, return the total score
		return totalScore;
	}
	
	/**
	 * Helper method to translate a bowl symbol into the number of pins knocked
	 * down:
	 * 	'-' ==> returns 0
	 * 	'1' through '9' ==> returns that number
	 * 	'X' ==> returns 10
	 * 	'/' ==> returns 10 minus the number of pins knocked down on the
	 * 		previous bowl.
	 * 
	 * @param index - The index (in bowls) of the specific bowl to get pins for
	 * @return The number of pins knocked down by that bowl
	 * @throws BowlingException 
	 */
	private int pins(int index){
		char bowl = bowls[index];
		
		switch(bowl){
		case '-': //miss
			return 0;
		case 'X': //strike
			return 10;
		case '/': //spare
			//TODO: check for spare at beginning of line (out of scope)
			//return 10 minus number of pins knocked down on previous bowl
			return 10 - pins(index-1);
		default:
			return Character.getNumericValue(bowl); //any number 1-9
		}
	}
	
}
