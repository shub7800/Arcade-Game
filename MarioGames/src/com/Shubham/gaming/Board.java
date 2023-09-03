package com.Shubham.gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.Shubham.gaming.sprites.Enemy;
import com.Shubham.gaming.sprites.Player;
import com.Shubham.gaming.sprites.Sprite;

public class Board extends JPanel implements Constants{
	
	// Constructor (Initlalize)
	BufferedImage bi ;
	Player player ;
	//Enemy enemy;
	Enemy enemies[] ;
	int x[] = new int[10];
	String gameMessage = "";
	Board() throws Exception{
		//Sprite sprite = new Sprite();
		setSize(BOARD_WIDTH, BOARD_HEIGHT); // Board Size Set
		bi = ImageIO.read(Board.class.getResource("bacgrounds.jpg")); // Image Read
		player = new Player();
		enemies = new Enemy[MAX_ENEMY]; // all enemies are null
		loadEnemies();
		setFocusable(true);
		bindEvents();
		gameLoop();
		
	
	}
	
	boolean isCollide(Sprite one, Sprite two){
		int xDistance = Math.abs(one.getX() - two.getX());
		int yDistance = Math.abs(one.getY() - two.getY());
		int w = Math.max(one.getW(), two.getW());
		int h = Math.max(one.getH(), two.getH());
		return xDistance<=(w-20) && yDistance<=(h-10);
		
	}
	
	void checkCollision() {
		// Check Player is Collide with Enemy or not
		for(Enemy e : enemies) {
			if(isCollide(player, e)) {
				gameMessage = "Game Over";
				timer.stop();
			}
		}
	}
	
	void isGameWin() {
		if(player.outOfScreen()) {
			gameMessage = "Game Win";
			timer.stop();
		}
	}
	
	
	
	void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("KeyPress.....");
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.move();
				}
				
			}
		});
	}
	Timer timer;
	
	void gameLoop(){
		
		// Delay
		 timer = new Timer(50,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				checkCollision();
				isGameWin();
				
				
			}
		});
		timer.start();
	}
	
	void loadEnemies() throws IOException {
		int x = 250;
		final int GAP = 250;
		int speed = 2;
		for (int i = 0 ; i<enemies.length; i++) {
			enemies[i] = new Enemy(x, speed);
			speed = speed + 4;
			x = x + GAP;
			
		}
	}
	
	
	
	void printEnemies(Graphics pen) {
		for(Enemy e : enemies) {
			e.draw(pen);
			e.move();
		}
	}
	
	void printMessage(Graphics pen) {
		pen.setColor(Color.RED);
		pen.setFont(new Font("times", Font.BOLD, 60));
		pen.drawString(gameMessage, BOARD_WIDTH/3,BOARD_HEIGHT/2);
	}
	
	// Painting on Board
	@Override
	public void  paintComponent(Graphics pen){
//		pen.setColor(Color.RED);
//		pen.fillRect(10, 50, 70, 50);
//		pen.drawRect(20, 100, 100, 100);
//		pen.drawOval(300, 50, 100, 100);
//		pen.setFont(new Font("times", Font.BOLD, 50));
//		pen.drawString("Game 2022", 300, 300);
		pen.drawImage(bi,0,0,BOARD_WIDTH,BOARD_HEIGHT, null);
		player.draw(pen);
		printEnemies(pen);
		if(gameMessage.length()>0) {
		printMessage(pen);
		}
		//enemy.draw(pen);
		
	}

}
