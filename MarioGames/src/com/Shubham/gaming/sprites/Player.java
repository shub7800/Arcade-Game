package com.Shubham.gaming.sprites;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.Shubham.gaming.Board;
import com.Shubham.gaming.Constants;

public class Player extends Sprite implements Constants{
	public Player() throws IOException{
		x= 20;
		h = 100;
		y = BOARD_HEIGHT -100 -h;
		w = 100;
		
		speed = 10;
		bi = ImageIO.read(Player.class.getResource("output-onlinegiftools.gif"));
	}
	@Override
	public void move() {
		x = x + speed;
	}
	public boolean outOfScreen() {
		if(x>BOARD_WIDTH) {
			return true;
		}
		return false;
	}
	
	
}
