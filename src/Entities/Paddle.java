package Entities;

import java.awt.Color;
import java.awt.Graphics;

import MainGame.Pong;

public class Paddle {

	@SuppressWarnings("unused")
	private int paddleNumber, score;

	private int x, y, width = 20, height = 180;
	private static final int SPEED = 15;

	public Paddle(Pong pong, int paddleNumber) {
		this.paddleNumber = paddleNumber;

		if (paddleNumber == 1)
			x = 3;
		if (paddleNumber == 2)
			x = pong.getWidth() - width-10;

		y = pong.getHeight() / 2 - height / 2;
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

	public void move(boolean up) {
		if (up)
			if (y - SPEED >= 0)
				y -= SPEED;
			else
				y = 0;

		else if (y + SPEED <= Pong.getInstance().getHeight() - height)
			y += SPEED;
		else
			y = Pong.getInstance().getHeight() - height;
	}
	
	public void addScore() {
		score++;
	}
	public int getScore() {
		return score;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNumber() {
		return paddleNumber;
	}

}
