package edu.nyu.cs.ma5264;

import edu.nyu.cs.ma5264.App;
import processing.core.*;


/**
 * The Cactus class represents a Cactus obstacle.
 * @author Muhammad Aziz
 * @version 1
 */
public class Cactus {
		private App app; //Reference
		
		//Variables pertaining to class
		private final static String CACTUS_IMAGE_PATH  = "cactus.png"; //alien image file
		private PImage img; //will hold the image to  use for this alien
		private static int numOfCacti = 0; //keeps track of number of cacti
		private static int totalDistance = 0; //keeps track of total distance that the cacti span from the origin x
		
		//Fixed variables used by the class
		private final static int CACTUS_DISTANCE_MAX = 900; //max distance between cacti 
		private final static int CACTUS_DISTANCE_MIN = 500; //min distance between cacti 
		
		
		
		/**
		 * Constructor for an Cactus object.  
		 * @param app A reference to the object that controls the flow of the game. 
		 */
		public Cactus(App app) {
			//set up initial properties for this  alien
			this.app = app; //keep a  reference to the PApplet class to handle  all Processing-specific functions and  variables
			//load the alien image and store in  PImage variable
			this.img = app.loadImage(Cactus.CACTUS_IMAGE_PATH);
			
			//SET POSTION
			//y is always 300 default position
			if (numOfCacti == 0) {
				this.xCord = xCord; //places the first cactus 
				numOfCacti++; // updates number of cacti
			} else { 
				this.distance = this.getRandomDistance(); //gets a random value difference
				totalDistance += distance; //sums up the distance between all cactuses
				this.xCord += totalDistance + ((numOfCacti)*(this.getWidth()/7)) ; //x position will be the new total distance added to the first cactus position and cactus widths 
				numOfCacti++; // updates number of cacti
			}
		}
		
		//PROPERTIES of CLASS
		private int xCord = 900 ; // will vary, but initial value is set
		private final int yCord = 250; //Fixed y coordinate
		private final int speedX = -20; // speed in x direction towards player
		private int distance; //distance between cacti
		
				
		//GETTERS
		/**
		 * Get the width of this cactus, based on the width of its image.
		 * @return int width of this image
		 */
		public int getWidth() {
			return this.img.width; //return the PImage object's width property
		}
		/**
		 * Get the height of this cactus, based on  the width of its image.
		 * @return int height of this image
		 */
		public int getHeight() {
			return this.img.height; //return the PImage object's height property
		}
		/**
		 * Get the x coordinate value
		 * @return int x value
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
		public int getSpeedX() {
			return this.speedX;
		}
		
		
		//SETTERS
		/**
		 * Sets int value for x coordinate
		 * @param int xcord
		 */
		public void setXcord(int xcord) {
			this.xCord = xcord;
		}
		//no setter for yCord because y coordinate can't be changed
		
	
		//BEHAVIORS
		/**
		 * Get a random speed.
		 * @return random int between min and max speed settings
		 */
		public int getRandomDistance() {
			int distance = (int) ((Math.random() * Cactus.CACTUS_DISTANCE_MAX) + Cactus.CACTUS_DISTANCE_MIN); 
			return distance;
		}
		
		/**
		 * Draws each cactus to the PApplet screen. 
		 */
		public void draw() {
			//draw the image using PApplet's  image method
			this.app.image(this.img, this.xCord, this.yCord, img.width/7, img.height/7);
		}
		
		/**
		 * Move this cactus towards the player
		 */
		public void move() {
			int newX = this.xCord + this.speedX; // calculate new x position
			//update to the new position
			this.setXcord(newX);
		}
		

}
