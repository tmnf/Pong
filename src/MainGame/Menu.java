package MainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Engine.Drawable;
import Engine.ResourceLoader;

public class Menu implements Drawable {

	private Pong pong;

	public Menu(Pong pong) {
		this.pong = pong;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(ResourceLoader.loadImage("retroimg.png"), 30, 110, null);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", 1, 80));
		g.drawString("Pong", pong.getWidth() / 2 - 110, 200);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", 1, 20));
		g.drawString("-- Pressione Espaço Para Jogar Com Dois Jogadores", pong.getWidth() / 2 - 230, 400);
		g.drawString("-- Pressione Shift Para Jogar Contra O Computador", pong.getWidth() / 2 - 230, 450);
		g.drawString("-- Pressione ESC Para Sair do Jogo", pong.getWidth() / 2 - 230, 500);
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", 1, 18));
		g.drawString("Velocidade: <<" + pong.getSpeed() + ">>", pong.getWidth() / 2 - 230, pong.getHeight() - 100);
		g.drawString("Dificuldade do Bot: <<" + pong.getDificuldade() + ">>", pong.getWidth() / 2 - 230,
				pong.getHeight() - 50);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", 1, 15));
		g.drawString("By: Tiago Farinha", pong.getWidth() - 280, pong.getHeight() - 100);

	}

}
