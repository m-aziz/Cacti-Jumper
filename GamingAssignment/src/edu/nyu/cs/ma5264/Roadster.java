package edu.nyu.cs.ma5264;

import edu.nyu.cs.ma5264.App;
import processing.core.*;

/**
 * 
 * This class represents a vehicle in the desert.
 * @author Muhammad Aziz
 * @version 1
 *
 */
public class Roadster {

	//will hold a reference to the App object.
	App app;
	
	//General Variables pertaining to class
	private final static String ROADSTER_IMAGE_PATH = "roadster.png"; 
	private final static String ROADSTER_IMAGE_PATH2 = "roadsterPurple.png"; 
	private final static String ROADSTER_IMAGE_PATH3 = "roadsterWheels.png"; 
	private final static String ROADSTER_IMAGE_PATH4 = "roadsterPurpleWheels.png"; 
	private PImage img; //will hold the image to use for the roadster
	
	private final int JUMPCEILING = 5; //the y coordinate height which roadster can jump
	private final int FLOOR = 200; //the y coordinate height which roadster uses as floor
	private final int JUMPSPEED = 12; //the y coordinate height which roadster uses as floor
	private static boolean crashed = false; //roadster has not collided
	
	
	/**
	 * Constructor for a roadster. 
	 * @param app A reference to the object that controls the flow of the game. 
	 */
	public Roadster(App app) {
		//set up initial properties for this  alien
		this.app = app; //keep a reference to the PApplet class.
		
		//load the image and store in PImage  variable
		this.img = this.app.loadImage(Roadster.ROADSTER_IMAGE_PATH);
	}
	
	/**
	 * Overloaded Constructor(1) for a roadster that might change color. 
	 * @param app A reference to the object that controls the flow of the game. 
	 * @param boolean defaultColor checks whether user wants default color of roadster.
	 */
	public Roadster(App app, boolean defaultColor) {
		this.app = app; //reference
		
		if (!defaultColor) {
			//load the purple image and store in PImage  variable
			this.img = this.app.loadImage(Roadster.ROADSTER_IMAGE_PATH2);
		} else { this.img = this.app.loadImage(Roadster.ROADSTER_IMAGE_PATH); }
	}
	
	/**
	 * Overloaded Constructor(2) for a roadster that might change color and obtain wheels
	 * @param app A reference to the object that controls the flow of the game. 
	 * @param boolean defaultColor checks whether user wants default color of roadster.
	 * @param boolean wheels checks whether to give the roadster wheels
	 */
	public Roadster(App app, boolean defaultColor, boolean wheels) {
		this.app = app; // reference
		
		if ((!defaultColor) && (wheels)) {
			//load the purple and wheel image and store in PImage  variable
			this.img = this.app.loadImage(Roadster.ROADSTER_IMAGE_PATH4);
		}
		else if ((defaultColor) && (wheels)) {
			//load the default color and wheel image and store in PImage  variable
			this.img = this.app.loadImage(Roadster.ROADSTER_IMAGE_PATH3);
		}
		else if ((!defaultColor) && (!wheels)) {
			//load the purple color without wheels image and store in PImage  variable
			this.img = this.app.loadImage(Roadster.ROADSTER_IMAGE_PATH2);
			
		} else { this.img = this.app.loadImage(Roadster.ROADSTER_IMAGE_PATH); }
	}
	
	//PROPERTIES of CLASS
	private boolean defaultColor = true; //will give red color
	private boolean wheels = false; // will not give wheels
	private final int xCord = 50; //roadster will not move in X direction
	private int yCord = FLOOR; //y coordinate will change; however default is set to 175 to be level with cacti
	private int speedY; //speed in Y direction, changes when jumping
	
	
	//GETTERS
	/**
	 * Get the width of the roadster, based on the width of its image.
	 */
	public int getWidth() {
		// the PImage object width property
		return this.img.width;
	}
	
	/**
	 * Get the height of this roadster, based on the width of its image.
	 */
	public int getHeight() {
		// the PImage object height property
		return this.img.height;
	}
	/**
	 * Get whether the roadster is a default color.
	 * @boolean
	 */
	public boolean getDefaultColor() {
		return this.defaultColor;
	}
	
	/**
	 * Get whether the roadster has wheels.
	 * @return boolean
	 */
	public boolean getWheels() {
		return this.wheels;
	}
	/**
	 * Get the x coordinate value
	 * @return int
	 */
	public int getXcord() {
		return this.xCord;
	}
	/**
	 * Get the y coordinate value
	 * @return int
	 */
	public int getYcord() {
		return this.yCord;
	}
	/**
	 * Get the y speed for the roadster 
	 * @return int
	 */
	public int getSpeedY() {
		return this.speedY;
	}
	
	
	//SETTERS
	/**
	 * Sets boolean value if roadster has the default color
	 * @param boolean defaultColor
	 */
	public void setDefaultColor(boolean defaultColor) {
		this.defaultColor = defaultColor;
	}
	/**
	 * Sets boolean value if roadster has wheels
	 * @param boolean wheels
	 */
	public void setWheels(boolean wheels) {
		this.wheels = wheels;
	}
	/**
	 * Sets int value for y coordinate
	 * @param int ycord
	 */
	public void setYcord(int ycord) {
		this.yCord = ycord;
	}
	/**
	 * Set the y speed for the roadster 
	 * @param int speedY
	 */
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	
	
	
	//BEHAVIORS
	/**
	 * Draws this roadster to the PApplet screen. 
	 */
	public void draw() {
		this.app.image(this.img,this.xCord ,this.yCord, img.width/3, img.height/3);
	}
	
	/**
	 * Makes the spaceship jump if space key is pressed.
	 */
	public void move() {
		
		if (this.getSpeedY() < 0) { //if speed is negative, roadster is jumping up currently
			// calculate move by whichever amount is  specified in speedY variable.
			int newY = this.getYcord() + this.getSpeedY();
			//Checks Bounds
			boolean outOfBoundsCeiling = newY < JUMPCEILING; //if it's too high up
			if (outOfBoundsCeiling) {
				 //reverse direction
				this.toggleDirection(); //inverts  the sign of speed (falls back on ground)
				newY = this.getYcord() + this.getSpeedY(); // calculate move in new direction
				//make update to position
				this.setYcord(newY);
			} else { this.setYcord(newY);}
		}
		
		else if (this.getSpeedY() > 0) { //speed is positive so roadster is falling back to the ground
			int newY = this.getYcord() + this.getSpeedY();
			boolean outOfBoundsFloor = newY > FLOOR ; //if it dips below the floor
			if (outOfBoundsFloor) {
				newY = FLOOR; //Sets to original floor of y coordinate
				//make update to position
				this.setYcord(newY);
				this.setSpeedY(0); //completes the jump, until space bar changes and triggers it again
			} else { this.setYcord(newY);}
		}
	}
	
	/**
	 * Initiates the jump for the roadster
	 */
	public void jump() {
		if (this.yCord == FLOOR) {
			//set the jump speed to speedY
			this.speedY = JUMPSPEED * -1;
		}
	}
	
	/**
	 * Move this spaceship in the opposite  direction from which it is currently moving
	 */
	public void toggleDirection() {
		this.speedY = -this.speedY; //invert the  sign of the speed it's currently moving
	}
	
	/**
	 * Static method to check collision between any cactus and the roadster
	 */
	public boolean isCollision(Roadster roadster, Cactus  cactus) {
		//check whether the roadsters x-coordinate is within the box representing the cactus
		if  (((cactus.getXcord() < (roadster.getXcord() + roadster.getWidth()/3)) && ((roadster.getXcord() + roadster.getWidth()/3) < (cactus.getXcord() + cactus.getWidth()/7)))){
			if ((roadster.getYcord() + roadster.getHeight()/3) > cactus.getYcord()) {
				crashed = true;
			}
		}
		return crashed;
	}

}
