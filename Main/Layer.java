package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Layer {

  GamePanel gp;

  public Layer(GamePanel gp) {
    this.gp = gp;
  }

  public void draw(Graphics2D g2) {
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, 192, 64);
    g2.fillRect(832, 0, 192, 64);

    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Roboto", Font.PLAIN, 24));
    String score = Integer.toString(gp.score);
    g2.drawString("SCORE:", 16, 40);
    g2.drawString(score, 160, 40);

    String speed = Integer.toString(gp.SPEED);
    g2.drawString("SPEED:", 848, 40);
    g2.drawString(speed, 992, 40);
  }
}
