package edu.nyu.cs.ma5264;

import edu.nyu.cs.ma5264.Cactus;
import edu.nyu.cs.ma5264.Roadster;
import java.util.ArrayList;
import processing.core.*;


/**
 * A simple class that inherits from Processing's PApplet class and displays an oscillating circle that bounces back and forth
 * @author Muhammad Aziz
 * @version 1
 */
public class App extends PApplet {
	/**
	 * Automatically called to start your program.  
	 * This method calls PApplet's main method and passes it the class name of this class.
	 * @param args Command-line arguments (ignored)
	 */
	public static void main(String[] args) {
		PApplet.main("edu.nyu.cs.ma5264.App");
	}
	
	//instance properties
	//constants that hold the width and height of the window
	final private int w= 950;
	final private int h = 450;
	//make constants for number of cactus (also determines length of game time)
	public final static int NUM_CACTUS = 20;
	//Keeps track of score
	private static int score;
	//variable indicating whether the game has ended and the roadster crashed
	private static boolean crash = false;
	//variable to hold the vehicle
	private Roadster roadster;
	//an array to hold cacti
	private ArrayList<Cactus> cacti = new ArrayList<Cactus>(); 
	
	//setters and getters
	/**
	 * Getter for the ArrayList of Cactus objects currently on the screen
	 * @return ArrayList of Cactus objects
	 */
	public ArrayList<Cactus> getCacti() {
		return this.cacti;
	}

	/**
	 * Setter for the ArrayList of Cactus objects currently on the screen.
	 * @param aliens An ArrayList of Cactus objects
	 */
	public void setCacti(ArrayList<Cactus> cacti) {
		this.cacti = cacti;
	}
	
	
	
	
	
	
	//MAIN PROGRAM RUNNING THE GAME
	/**
	 * Method to draft general settings of the app
	 */
	public void settings() {
		this.size(this.w, this.h); //window size
	}
	
	
	/**
	 * Method to compose the first 'frame' of the app
	 */
	public void setup() {
		//make background variable and load image
		PImage background = loadImage("Desert_Background.jpg");
		background.resize(950,450);
		this.background(background); //set background  color
		
		//initialize roadster
		this.roadster = new Roadster(this, false, true); // pass reference to this App object
		
		//Initialize all cacti depending on NUM_CACTUS
		for (int i=0; i<App.NUM_CACTUS; i++) {
					
			//create a new cactus for each  element of the array
			Cactus cactus = new Cactus(this);  //pass a reference to this App class
			this.cacti.add(cactus); //add this  cactus to the array list
		}
	}
	
	
	/**
	 * Called repeatedly approximately 60 times per second (Processing's default "frame  rate").  
	 * Used to update the animation and  enforce game play logic.
	 */
	public void draw() {
		//wipe the screen blank
		PImage background = loadImage("Desert_Background.jpg");
		background.resize(950,450);
		this.background(background); //set background  color
		
		if (!crash) {
		
				//keeps track of score
			score = (int)(millis()/1000);
		
			//draw the roadster
			this.roadster.move(); //have the spaceship move itself to a new location
			this.roadster.draw(); //have the spaceship draw itself to the screen

			//loop through ArrayList of cacti
			for (int i=0; i<this.cacti.size(); i++) {
				Cactus cactus = this.cacti.get(i);
				cactus.move(); //have the cacti move itself to a new location
				cactus.draw(); //have the cacti draw itself to the screen
			}
			//detect any collisions between the roadster and cacti
			for (Cactus cactus : this.cacti) {
				//Roadster class has a static  method that checks whether there  is a collision
				if (roadster.isCollision(roadster,cactus)) {
					//if there is a collision
					crash=true;
				}
			} 
		} else {
				//prints out score once the player crashes
				textSize(25);
				stroke(153);
				text("You Crashed! Game Over", 470, 150);
				fill(0);
				text("Your Score:", 470, 185);
				text(score, 470, 210);
				fill(150, 0, 0);
				textAlign(CENTER);
			}
	}
	
	
	/**
	 * Called whenever a key is pressed by the user. Makes the roadster jump when space is pressed.
	 */
	public void keyPressed() {
		if (key == ' ') {
			//handle space key
			this.roadster.jump();
		}
	}
}
