package Entities;

import MainGame.Pong;

public class Bot extends Paddle {

	private int speed;

	public Bot(Pong pong, int paddleNumber) {
		super(pong, paddleNumber);
		speed = 2;
	}

	public void update(Ball ball) {
		if (ball.getY() > getY() + getHeight() / 2)
			if (getY() + speed + getHeight() + 20 <= Pong.getInstance().getHeight())
				setY(getY() + speed);
		if (ball.getY() < getY() + getHeight() / 2)
			if (getY() - speed >= 0)
				setY(getY() - speed);

	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
