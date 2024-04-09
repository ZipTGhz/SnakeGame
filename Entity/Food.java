package Entity;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Food extends Entity {

  public Food() {
    initFoodImage();
  }

  private void initFoodImage() {
    try {
      BufferedImage image = ImageIO.read(
        getClass().getResourceAsStream("/img/food.png")
      );
      setImage(image);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setPosition(int x, int y) {
    setX(x);
    setY(y);
  }
}
