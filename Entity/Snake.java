package Entity;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Snake extends Entity {

  public Snake(int x, int y) {
    setX(x);
    setY(y);
    initSnakeImage();
  }

  private void initSnakeImage() {
    try {
      BufferedImage image = ImageIO.read(
        getClass().getResourceAsStream("/img/box.png")
      );
      setImage(image);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
