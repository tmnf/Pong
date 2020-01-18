package MainGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Engine.Drawable;

public class CurrentGame implements Drawable {

	private Pong pong;
	private int gameStatus;

	public CurrentGame(Pong pong, int state) {
		this.pong = pong;
		gameStatus = state;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", 1, 50));
		g.drawString(String.valueOf(pong.getPlayer1().getScore()), pong.getWidth() / 2 - 70, 50);
		if (pong.getBotState())
			g.drawString(String.valueOf(pong.getBot().getScore()), pong.getWidth() / 2 + 10, 50);
		else
			g.drawString(String.valueOf(pong.getPlayer2().getScore()), pong.getWidth() / 2 + 10, 50);

		g.setFont(new Font("Arial", 1, 20));
		g.drawString("Fps: " + String.valueOf(pong.getGame().getFps()), 30, 40);

		g.setStroke(new BasicStroke(3f));
		g.drawLine(pong.getWidth() / 2, 0, pong.getWidth() / 2, pong.getHeight());
		g.drawOval(pong.getWidth() / 2 - 75, pong.getHeight() / 2 - 75, 150, 150);

		pong.getPlayer1().render(g);
		if (!pong.getBotState())
			pong.getPlayer2().render(g);
		else
			pong.getBot().render(g);
		pong.getBall().render(g);

		if (gameStatus == 0) {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", 1, 80));
			g.drawString("Pausa", pong.getWidth() / 2 - 120, pong.getHeight() / 2);
		}

	}

}
