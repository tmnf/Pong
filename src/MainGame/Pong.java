package MainGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Engine.GameLoop;
import Engine.Renderer;
import Engine.ResourceLoader;
import Engine.Updatable;
import Entities.Ball;
import Entities.Bot;
import Entities.Paddle;

public class Pong implements KeyListener, Updatable {

	private static Pong pong;
	private Renderer renderer;

	private Paddle player1, player2;
	private Bot bot;

	private Ball ball;
	private GameLoop<Updatable> game;

	private boolean w, s, up, down, botActive;

	private int gameStatus, playerWon, speed, dificuldade;
	private static final int VICTORY = 15, WIDTH = 700, HEIGHT = 700;

	public Pong() {

		JFrame jframe = new JFrame("Pong");
		renderer = new Renderer(this);

		gameStatus = 1;
		speed = 1;
		dificuldade = 1;

		ResourceLoader.playSound("retromusic.wav");

		jframe.setSize(WIDTH, HEIGHT);
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.add(renderer);
		jframe.addKeyListener(this);
		game = new GameLoop<>(this);

	}

	public void start() {
		gameStatus = 2;
		player1 = new Paddle(this, 1);
		if (!botActive)
			player2 = new Paddle(this, 2);
		else {
			bot = new Bot(this, 2);
			bot.setSpeed(dificuldade);
		}

		ball = new Ball(this);
		ball.setSpeed(speed);
	}

	public void update() {
		if (gameStatus == 2) {
			if (player1.getScore() == VICTORY) {
				playerWon = 1;
				gameStatus = 3;
			}

			if (w)
				player1.move(true);
			if (s)
				player1.move(false);

			if (!botActive) {
				if (player2.getScore() == VICTORY) {
					playerWon = 2;
					gameStatus = 3;
				}

				if (up)
					player2.move(true);
				if (down)
					player2.move(false);

				ball.update(player1, player2);
			} else
				botBehave();
		}
	}

	private void botBehave() {
		if (bot.getScore() == VICTORY) {
			playerWon = 2;
			gameStatus = 3;
		}
		ball.update(player1, bot);
		bot.update(ball);
	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (gameStatus == 1)
			new Menu(this).render(g);
		if (gameStatus == 2 || gameStatus == 0)
			new CurrentGame(this, gameStatus).render(g);
		if (gameStatus == 3)
			new Victory(this).render(g);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();

		if (id == KeyEvent.VK_W && gameStatus == 2)
			w = true;
		if (id == KeyEvent.VK_S && gameStatus == 2)
			s = true;

		if (id == KeyEvent.VK_UP) {
			if (gameStatus == 2)
				up = true;
			else if (gameStatus == 1)
				if (dificuldade <= 9)
					dificuldade++;
		}
		if (id == KeyEvent.VK_DOWN) {
			if (gameStatus == 2)
				down = true;
			else if (gameStatus == 1)
				if (dificuldade >= 2)
					dificuldade--;
		}

		if (id == KeyEvent.VK_RIGHT && gameStatus == 1)
			if (speed <= 11)
				speed++;
		if (id == KeyEvent.VK_LEFT && gameStatus == 1)
			if (speed >= 2)
				speed--;

		if (id == KeyEvent.VK_SHIFT && gameStatus == 1) {
			botActive = true;
			start();
		}

		if (id == KeyEvent.VK_SPACE) {
			if (gameStatus == 1) {
				botActive = false;
				start();
			} else if (gameStatus == 0)
				gameStatus = 2;
			else if (gameStatus == 2)
				gameStatus = 0;
		}
		if (id == KeyEvent.VK_ESCAPE) {
			if (gameStatus == 1)
				System.exit(0);
			if (gameStatus == 2 || gameStatus == 3 || gameStatus == 0)
				gameStatus = 1;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();

		if (id == KeyEvent.VK_W)
			w = false;
		if (id == KeyEvent.VK_S)
			s = false;
		if (id == KeyEvent.VK_UP)
			up = false;
		if (id == KeyEvent.VK_DOWN)
			down = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// Getters and Setters

	public static Pong getInstance() {
		if (pong == null)
			pong = new Pong();
		return pong;
	}

	@Override
	public Renderer getRenderer() {
		return renderer;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDificuldade() {
		return dificuldade;
	}

	public int getWinner() {
		return playerWon;
	}

	public boolean getBotState() {
		return botActive;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public Paddle getPlayer1() {
		return player1;
	}

	public Paddle getPlayer2() {
		return player2;
	}

	public Paddle getBot() {
		return bot;
	}

	public Ball getBall() {
		return ball;
	}

	public GameLoop<Updatable> getGame() {
		return game;
	}

	public static void main(String[] args) {
		getInstance();
	}

}
