package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

	static final int screenWidth = 600;
	static final int screenHeight = 600;
	static final int unitSize = 25;								// this defines how big the objects in the game are
	static final int gameUnits = (screenWidth * screenHeight) / unitSize;
	static final int delayTimer = 75;
	
	// creates an array that will hold the size of the snake on the x and y axis, setting it to gameunits makes it
	// so that the snake's body will never be bigger than the size of the screen
	final int bodySizeX[] = new int[gameUnits];
	final int bodySizeY[] = new int[gameUnits];
	int bodyParts = 6;
	
	int applesEaten;
	int applePositionX;
	int applePositionY;
	
	char snakeDirection = 'R';
	
	boolean running = false;
	Timer timer;
	Random random;
	
	// constructor
	GamePanel() {
	
		// continue working here @12:30
	}
	
	public void startGame() {
		
	}
	
	public void paintComponent(Graphics graphics) {
		
	}
	
	public void draw(Graphics graphics) {
		
	}
	
	public void move() {
		
	}
	
	public void checkApple() {
		
	}
	
	public void checkCollisions() {
		
	}
	
	public void gameOver(Graphics graphics) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			
		}
	}
}
