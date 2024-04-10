package Entity;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Food extends Entity {

  public BufferedImage image;

  public Food() {
    initFoodImage();
  }

  private void initFoodImage() {
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/img/food.png"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setPosition(int x, int y) {
    setX(x);
    setY(y);
  }
}
