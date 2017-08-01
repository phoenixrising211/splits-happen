package models;

/**
 * Model which represents a line of bowling. Has methods to score the game and
 * to translate throw symbols into number of pins.
 * 
 * @author aaron.paloski
 *
 */
public class BowlingLine {

	private char[] balls;
	
	public BowlingLine(String line){
		//TODO: validate input (out of scope)
		balls = line.toCharArray();
	}
	
	/**
	 * Scores the line of bowling. See README for scoring logic.
	 * 
	 * @return Total score of the game
	 */
	public int score(){
		int totalScore = 0;
		
		/*
		 * Counting frames and balls is out of scope, but it's the easiest way
		 * to account for bonus balls at the end of the line.
		 */
		int frame = 1; 
		boolean secondBall = false;
		
		int current = 0; //current index in balls array
		while(frame <= 10){
			//first, add the number of pins knocked down on this throw
			totalScore += pins(current);
			
			//next, handle special logic for spares and strikes
			char ball = balls[current];
			
			switch(ball){
			case '/': //spare
				//TODO: check for missing bonus ball (out of scope)
				//add the number of pins knocked down on the next throw.
				totalScore += pins(current+1);
				break;
				
			case 'X': //strike
				//TODO: check for missing bonus ball (out of scope)
				//add the number of pins knocked down on the next two throws.
				totalScore += pins(current+1);
				totalScore += pins(current+2);
				break;
			}
			
			//finally, advance current position, frame number, and secondBall
			current++;
			if(secondBall || ball == 'X'){
				 //if this was the second ball or a strike, go to next frame
				secondBall = false;
				frame++;
			}else{
				//otherwise, it's now the second ball
				secondBall = true;
			}
		}
		
		//after 10 frames, return the total score
		return totalScore;
	}
	
	/**
	 * Helper method to translate a throw symbol into the number of pins knocked
	 * down:
	 * 		'-' ==> returns 0
	 * 		'1' through '9' ==> returns that number
	 * 		'X' ==> returns 10
	 * 		'/' ==> returns 10 minus the number of pins knocked down on the
	 * 			previous throw.
	 * 
	 * @param index - The index (in balls) of the throw to get pins for
	 * @return The number of pins knocked down by that throw
	 */
	private int pins(int index){
		char ball = balls[index];
		
		switch(ball){
		case '-': //miss
			return 0;
		case 'X': //strike
			return 10;
		case '/': //spare
			//TODO: check for spare at beginning of line (out of scope)
			//return 10 minus number of pins knocked down on previous throw
			return 10 - pins(index-1);
		default:
			return Character.getNumericValue(ball); //any number 1-9
		}
	}
	
}
