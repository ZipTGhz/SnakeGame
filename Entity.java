import java.awt.Image;

public class Entity {

  private int x, y;
  private Image image;

  Entity() {}

  Entity(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Image getImage() {
    return image;
  }
}
