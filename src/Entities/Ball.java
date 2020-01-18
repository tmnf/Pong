package Entities;

import java.awt.Graphics;
import java.util.Random;

import Engine.ResourceLoader;
import MainGame.Pong;

public class Ball {

	private int x, y, width, height;
	private Pong pong;
	private int SPEED;
	private int motionX, motionY;
	private Random rnd;
	private int hits;
	private String animation;

	public Ball(Pong pong) {
		this.pong = pong;
		width = 48;
		height = 48;
		rnd = new Random();
		spawn();
		animation = "redimg.png";
	}

	public void update(Paddle player1, Paddle player2) {
		x += motionX * SPEED;
		y += motionY * SPEED;

		if (y - 18 < 0 || y + height + 20 > pong.getHeight())
			motionY = -motionY;

		if (checkCollision(player1) == 1) {
			ResourceLoader.playSound("bounce.wav");

			motionX = 1 + (hits / 3);
			hits++;
		} else if (checkCollision(player2) == 1) {
			ResourceLoader.playSound("bounce.wav");
			motionX = -1 - (hits / 3);
			hits++;
		}

		if (checkCollision(player1) == 2) {
			ResourceLoader.playSound("point.wav");
			player2.addScore();
			spawn();
		} else if (checkCollision(player2) == 2) {
			ResourceLoader.playSound("point.wav");
			player1.addScore();
			spawn();
		}

		if (hits == 4)
			animation = "hitball.png";

	}

	public int checkCollision(Paddle paddle) {
		if (x + 4 < paddle.getX() + paddle.getWidth() && x + width - 20 > paddle.getX()
				&& y < paddle.getY() + paddle.getHeight() && y + height > paddle.getY()) {
			return 1; // bounce
		} else if ((paddle.getX() > x && paddle.getNumber() == 1)
				|| (paddle.getX() < x + 5 && paddle.getNumber() == 2)) {
			return 2; // score
		}
		return 0; // nothing
	}

	public void spawn() {
		x = pong.getWidth() / 2;
		y = pong.getHeight() / 2;

		hits = 0;
		motionY = -2 + rnd.nextInt(4);
		
		animation = "redimg.png";

		if (motionY == 0)
			motionY = 1;
		if (rnd.nextBoolean())
			motionX = 1;
		else
			motionX = -1;
	}

	public void render(Graphics g) {
		g.drawImage(ResourceLoader.loadImage(animation), x, y, null);
	}

	public void setSpeed(int speed) {
		this.SPEED = speed;
	}

	public int getY() {
		return y;
	}

}
