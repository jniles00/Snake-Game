package main;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	GameFrame() {
		
		GamePanel gamePanel = new GamePanel();
		
		this.add(new GamePanel());
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();										// takes all the JFrame components and fits it into the frame
		this.setVisible(true);
		this.setLocationRelativeTo(null);					// sets the window to appear in the middle of the screen
	}

}
