package Entity;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Snake extends Entity {

  public BufferedImage headLeft, headRight, headUp, headDown;
  public BufferedImage tailLeft, tailRight, tailUp, tailDown;
  public BufferedImage bodyVertical, bodyHorizontal, bodyTopLeft, bodyTopRight, bodyBottomLeft, bodyBottomRight;

  public Snake(int x, int y) {
    setX(x);
    setY(y);
    initSnakeImage();
  }

  private void initSnakeImage() {
    try {
      headLeft =
        ImageIO.read(getClass().getResourceAsStream("/img/head_left.png"));
      headRight =
        ImageIO.read(getClass().getResourceAsStream("/img/head_right.png"));
      headUp = ImageIO.read(getClass().getResourceAsStream("/img/head_up.png"));
      headDown =
        ImageIO.read(getClass().getResourceAsStream("/img/head_down.png"));

      tailLeft =
        ImageIO.read(getClass().getResourceAsStream("/img/tail_left.png"));
      tailRight =
        ImageIO.read(getClass().getResourceAsStream("/img/tail_right.png"));
      tailUp = ImageIO.read(getClass().getResourceAsStream("/img/tail_up.png"));
      tailDown =
        ImageIO.read(getClass().getResourceAsStream("/img/tail_down.png"));

      bodyVertical =
        ImageIO.read(getClass().getResourceAsStream("/img/body_vertical.png"));
      bodyHorizontal =
        ImageIO.read(
          getClass().getResourceAsStream("/img/body_horizontal.png")
        );
      bodyTopLeft =
        ImageIO.read(getClass().getResourceAsStream("/img/body_topleft.png"));
      bodyTopRight =
        ImageIO.read(getClass().getResourceAsStream("/img/body_topright.png"));
      bodyBottomLeft =
        ImageIO.read(
          getClass().getResourceAsStream("/img/body_bottomleft.png")
        );
      bodyBottomRight =
        ImageIO.read(
          getClass().getResourceAsStream("/img/body_bottomright.png")
        );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
