import javax.swing.ImageIcon;

public class Snake extends Entity {

  Snake(int x, int y) {
    super(x, y);
    initSnake();
  }

  private void initSnake() {
    ImageIcon icon = new ImageIcon(getClass().getResource("/img/box.png"));
    setImage(icon.getImage());
  }

  public void move(int x, int y) {
    setX(getX() + x);
    setY(getY() + y);
  }
}
