package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

	static final int screenWidth = 600;
	static final int screenHeight = 600;
	static final int unitSize = 25;											// this defines how big the objects in the game are
	static final int gameUnits = (screenWidth * screenHeight) / unitSize;
	static final int delayTimer = 75;
	
	// creates an array that will hold the size of the snake on the x and y axis, setting it to gameUnits makes it
	// so that the snake's body will never be bigger than the size of the screen
	final int bodySizeX[] = new int[gameUnits];
	final int bodySizeY[] = new int[gameUnits];
	int bodyParts = 6;
	
	int foodEaten;
	int foodPosX;
	int foodPosY;
	
	char snakeDirection = 'R';
	
	boolean running = false;
	Timer timer;
	Random random;
	
	// constructor
	GamePanel() {
	
		random = new Random();
		
		// sets the size for the game panel
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		
		// starts the game after everything above has run
		startGame();
	}
	
	public void startGame() {
		
		newFood();								// calls the newFood function to spawn new food
		running = true;							// allows the player to start moving
		timer = new Timer(delayTimer, this);	// tells the game how fast to run
		timer.start();
	}
	
	public void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		draw(graphics);
	}
	
	public void draw(Graphics graphics) {
		
		// creates a grid by drawing lines across the game panel
		for(int i = 0; i < screenHeight / unitSize; i++) {
			graphics.drawLine(i*unitSize, 0, i*unitSize, screenHeight);
			graphics.drawLine(0,i*unitSize, screenWidth, i*unitSize);
		}
		
		// sets the colour of the food
		graphics.setColor(Color.white);
		
		// sets the size and position of the food 
		graphics.fillRect(foodPosX, foodPosY, unitSize, unitSize);
		
	}
	
	public void move() {
		
	}
	
	public void newFood() {
		// sets the range of the random to be the size of the screen divided by screenWidth/screenHeight. it is then cast as an int
		foodPosX = random.nextInt((int)screenWidth / unitSize) * unitSize;
		foodPosY = random.nextInt((int)screenHeight / unitSize) * unitSize;
	}
	
	public void checkFood() {
		
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
