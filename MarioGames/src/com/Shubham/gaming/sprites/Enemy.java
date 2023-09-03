package com.Shubham.gaming.sprites;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.Shubham.gaming.Constants;

public class Enemy extends Sprite implements Constants {
	public Enemy(int x,int speed) throws IOException{
		this.x= x;
		y = 50;
		w = 100;
		h = 100;
		this.speed = speed;
		bi = ImageIO.read(Enemy.class.getResource("spider.png"));
	}
	@Override
	public void move() {
		y = y + speed;
		outOfScreen();
	}
	public void outOfScreen() {
		if(y>BOARD_HEIGHT) {
			y = 0;
		}
	}
	
}
