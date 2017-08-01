package controllers;

import java.util.Scanner;

import models.BowlingLine;

/**
 * A simple text controller for the "Splits Happen" bowling scorer.
 * See README for documentation.
 * 
 * @author aaron.paloski
 *
 */
public class SplitsHappen {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);		
		
		System.out.println("Welcome to Splits Happen!");
		System.out.print("Enter your bowling line: ");
		
		String input = scan.nextLine();
		BowlingLine line = new BowlingLine(input);		
		int finalScore = line.score();
		System.out.println("Your bowling score is " + finalScore + "!");
		
		scan.close();
	}
	
}
