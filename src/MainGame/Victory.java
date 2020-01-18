package MainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Engine.Drawable;

public class Victory implements Drawable {

	private Pong pong;

	public Victory(Pong pong) {
		this.pong = pong;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", 1, 80));
		g.drawString("Vencedor!!", pong.getWidth() / 2 - 200, 100);
		g.setFont(new Font("Arial", 1, 40));
		if (pong.getWinner() == 1)
			g.drawString("Jogador 1", pong.getWidth() / 2 - 80, 400);
		else if (!pong.getBotState())
			g.drawString("Jogador 2", pong.getWidth() / 2 - 80, 400);
		else
			g.drawString("CPU", pong.getWidth() / 2 - 80, 400);
		g.setFont(new Font("Arial", 1, 15));
		g.drawString("Pressione ESC para voltar ao menu", pong.getWidth() / 2 - 110, 450);
	}

}
