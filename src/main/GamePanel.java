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
		
		if(running)
			{
			// creates a grid by drawing lines across the game panel
			for(int i = 0; i < screenHeight / unitSize; i++) {
				graphics.drawLine(i*unitSize, 0, i*unitSize, screenHeight);
				graphics.drawLine(0,i*unitSize, screenWidth, i*unitSize);
			}
			
			// sets the colour of the food
			graphics.setColor(Color.white);
			
			// sets the size and position of the food 
			graphics.fillRect(foodPosX, foodPosY, unitSize, unitSize);
			
			for(int i = 0; i < bodyParts; i++) {
				// Checks to see if i is equal to the head of the snake (0)
				if(i == 0) {
					graphics.setColor(Color.green);
					graphics.fillRect(bodySizeX[i], bodySizeY[i], unitSize, unitSize);
				}
				else {
					// This will be the body of the snake
					graphics.setColor(new Color(107,142,35));
					graphics.fillRect(bodySizeX[i], bodySizeY[i], unitSize, unitSize);
				}
			}
		}
		else {
			gameOver(graphics);
		}
	}
	
	public void move() {
		
		// iterates through all the body parts of the snake
		for(int i = bodyParts; i > 0; i--) {
			// shifts all the coordinates of the array by one spot
			bodySizeX[i] = bodySizeX[i-1];
			bodySizeY[i] = bodySizeY[i-1];
		}
		
		switch(snakeDirection) {
		case 'U':
			// array position 0 = head of the snake, moves it one unit size up
			bodySizeY[0] = bodySizeY[0] - unitSize;
			break;
		case 'D':
			// moves the head one position down
			bodySizeY[0] = bodySizeY[0] + unitSize;
			break;
		case 'L':
			// moves the head one position left
			bodySizeX[0] = bodySizeX[0] - unitSize;
			break;
		case 'R':
			// moves the head one position right
			bodySizeX[0] = bodySizeX[0] + unitSize;
			break;
		}
	}
	
	public void newFood() {
		// sets the range of the random to be the size of the screen divided by screenWidth/screenHeight. it is then cast as an int
		foodPosX = random.nextInt((int)screenWidth / unitSize) * unitSize;
		foodPosY = random.nextInt((int)screenHeight / unitSize) * unitSize;
	}
	
	public void checkFood() {
		// checks to see if the head of the snake has collided with the food, 
		if((bodySizeX[0] == foodPosX) && (bodySizeY[0] == foodPosY)) {
			bodyParts++;				// increases the snake's size
			foodEaten++;				// increases the score 
			newFood();					// calls the new food function to spawn more food
		}
		
	}
	
	public void checkCollisions() {
		
		for(int i = bodyParts; i > 0; i--) {
			// checks to see if the head has collided with the body
			if((bodySizeX[0] == bodySizeX[i]) && (bodySizeY[0] == bodySizeY[i])) {
				// stops the game running
				running = false;
			}
		}
		
		// checks if the head has collided with the left border
		if(bodySizeX[0] < 0) {
			running = false;
		}
		
		// checks if the head has collided with the right border
		if(bodySizeX[0] > screenWidth) {
			running = false;
		}
		
		// checks if the head has collided with the top border
		if(bodySizeY[0] < 0) {
			running = false;
		}
		
		// checks if the head has collided with the bottom border
		if(bodySizeY[0] > screenHeight) {
			running = false;
		}
		
		// stops timer if the game is not running
		if(!running) {
			timer.stop();
		}
		
	}
	
	public void gameOver(Graphics graphics) {
		
		// Game Over text
		graphics.setColor(new Color (178,34,34));
		graphics.setFont(new Font("Ink Free", Font.BOLD, 100));
		// prints the string "Game Over" and sets it to be displayed in the middle of the screen
		FontMetrics fontMetrics = getFontMetrics(graphics.getFont());
		graphics.drawString("Game Over", (screenWidth - fontMetrics.stringWidth("Game Over")) / 2, screenHeight / 2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Checks if the game is running
		if(running ) {
			// checks if the snake has run into some food, followed by the collisions
			move();
			checkFood();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				// this check is in place to stop the player from doing 180 turns
				if(snakeDirection != 'R') {
					snakeDirection = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(snakeDirection != 'L') {
					snakeDirection = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(snakeDirection != 'D') {
					snakeDirection = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(snakeDirection != 'U') {
					snakeDirection = 'D';
				}
				break;
			}
		}
	}
}
